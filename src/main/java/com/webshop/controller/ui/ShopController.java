package com.webshop.controller.ui;

import com.webshop.anotation.ActiveUser;
import com.webshop.controller.base.CommonController;
import com.webshop.model.classes.*;
import com.webshop.service.interfaces.ICategoryService;
import com.webshop.service.interfaces.ICustomerService;
import com.webshop.service.interfaces.IProductService;
import com.webshop.service.interfaces.ISubcategoryService;
import com.webshop.util.filterFacade.interfaces.IFilterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ShopController extends CommonController {

    //region Services
    @Autowired
    private IProductService productService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ISubcategoryService subcategoryService;
    //endregion

    //region Goods

    /**
     * Redirecting to shp/goods
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "goods")
    public String toHomePage(@ActiveUser User user, HttpSession session) {
        updateSession(user, session);
        return "shop/goods";
    }

    /**
     * Get good by id
     *
     * @param user
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public ModelAndView getProductDetail(@ActiveUser User user, @PathVariable("id") String id, HttpSession session) {
        ModelAndView modelAndView = getModel("shop/productDetail");
        updateSession(user, session);
        Integer productIntegeId = getIdFromString(id);
        if (productIntegeId != null) {
            Product product = productService.get(productIntegeId);
            modelAndView.addObject("product", product);
            modelAndView.addObject("categories", categoryService.getAll());
            modelAndView.addObject("category", product.getSubcategoryGroupBySubcategoryGroupId().getSubcategoryBySubcategoryId().getCategoryByCategoryId());
            modelAndView.addObject("subcategory", product.getSubcategoryGroupBySubcategoryGroupId().getSubcategoryBySubcategoryId());
            modelAndView.addObject("group", product.getSubcategoryGroupBySubcategoryGroupId());
        }
        return modelAndView;
    }

    /**
     * Retrieving all needed info for quick View
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/goods/quickView", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getQuickViewContent(@RequestParam(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.get(id);
        modelAndView.addObject("product", product);
        modelAndView.addObject("category", product.getSubcategoryGroupBySubcategoryGroupId().getSubcategoryBySubcategoryId().getCategoryByCategoryId());
        modelAndView.addObject("subcategory", product.getSubcategoryGroupBySubcategoryGroupId().getSubcategoryBySubcategoryId());
        modelAndView.addObject("group", product.getSubcategoryGroupBySubcategoryGroupId());
        modelAndView.setViewName("modal/partial/quickViewContentPartial");
        return modelAndView;
    }

    /**
     * Addint good to cart. id is needed
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/goods/addToCart", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addToCart(@RequestParam(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.get(id);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("modal/partial/addProductToCartPartial");
        return modelAndView;
    }
    //endregion

    /**
     * Filtering all category goods. All @RequestParam are not needed.
     * If they are not specified then all goods will be
     * returned
     *
     * @param user
     * @param id
     * @param request
     * @param propertiesIds
     * @param groupsIds
     * @param priceFrom
     * @param priceTo
     * @param sortBy
     * @param sortDirection
     * @param limit
     * @param page
     * @param session
     * @return
     */
    @RequestMapping(value = "/categories/{id}/goods", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getProductsByCategoryId
    (@ActiveUser User user,
     @PathVariable("id") Integer id, HttpServletRequest request,
     @RequestParam(value = "properties", required = false) List<String> propertiesIds,
     @RequestParam(value = "groups", required = false) List<String> groupsIds,
     @RequestParam(value = "priceFrom", required = false) Double priceFrom,
     @RequestParam(value = "priceTo", required = false) Double priceTo,
     @RequestParam(value = "sortBy", required = false) String sortBy,
     @RequestParam(value = "sortDirection", required = false) String sortDirection,
     @RequestParam(value = "limit", required = false) Integer limit,
     @RequestParam(value = "page", required = false) Integer page,
     HttpSession session) {
        ModelAndView modelAndView = getModel("shop/products");
        updateSession(user, session);
        if (id != null) {
            Category category = categoryService.get(id);
            if (category != null) {
                if (page == null || page <= 0) page = 1;
                List<Product> goods = category.getGoods();
                List<Property> properties = getPropertiesBySIds(propertiesIds);
                List<SubcategoryGroup> groups = getGroupsBySIds(groupsIds);
                IFilterFacade.ExtendedFiltering extendedFiltering = extendedFilteringProducts(page, limit, goods, properties, groups, priceFrom, priceTo, sortBy, sortDirection);
                goods = extendedFiltering.getGoods();

                addPriceRangeToModel(goods, modelAndView);

                //adding attributes for initializing header form
                addHeaderFormAttributes(sortBy, sortDirection, limit, page, extendedFiltering.getTotal(), modelAndView);

                modelAndView.addObject("goods", goods);
                modelAndView.addObject("category", category);
                Map<Integer, PropertyClass> propertyClassMap = retrieveRelatedProperties(goods, properties);
                modelAndView.addObject("propertyClassMap", propertyClassMap);
            }
            modelAndView.addObject("categories", categoryService.getAll());
        }
        return modelAndView;
    }

    /**
     * Filter all subcategory goods
     *
     * @param user
     * @param id
     * @param request
     * @param propertiesIds
     * @param groupsIds
     * @param priceFrom
     * @param priceTo
     * @param sortBy
     * @param sortDirection
     * @param limit
     * @param page
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/subcategories/{id}/goods", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getProductsBySubcategoryId
    (@ActiveUser User user,
     @PathVariable("id") Integer id, HttpServletRequest request,
     @RequestParam(value = "properties", required = false) List<String> propertiesIds,
     @RequestParam(value = "groups", required = false) List<String> groupsIds,
     @RequestParam(value = "priceFrom", required = false) Double priceFrom,
     @RequestParam(value = "priceTo", required = false) Double priceTo,
     @RequestParam(value = "sortBy", required = false) String sortBy,
     @RequestParam(value = "sortDirection", required = false) String sortDirection,
     @RequestParam(value = "limit", required = false) Integer limit,
     @RequestParam(value = "page", required = false) Integer page,
     HttpSession session) throws Exception {
        ModelAndView modelAndView = getModel("shop/products");
        updateSession(user, session);
        if (id != null) {
            Subcategory subcategory = subcategoryService.get(id);
            if (subcategory != null) {
                if (page == null || page <= 0) page = 1;
                List<Product> goods = subcategory.getGoods();
                List<Property> properties = getPropertiesBySIds(propertiesIds);
                List<SubcategoryGroup> groups = getGroupsBySIds(groupsIds);

                IFilterFacade.ExtendedFiltering extendedFiltering = extendedFilteringProducts(page, limit, goods, properties, groups, priceFrom, priceTo, sortBy, sortDirection);
                goods = extendedFiltering.getGoods();

                addPriceRangeToModel(goods, modelAndView);

                //adding attributes for initializing header form
                addHeaderFormAttributes(sortBy, sortDirection, limit, page, extendedFiltering.getTotal(), modelAndView);

                modelAndView.addObject("goods", goods);
                modelAndView.addObject("category", subcategory.getCategoryByCategoryId());
                modelAndView.addObject("subcategory", subcategory);
                Map<Integer, PropertyClass> propertyClassMap = retrieveRelatedProperties(goods, properties);
                modelAndView.addObject("propertyClassMap", propertyClassMap);
            }
            modelAndView.addObject("categories", categoryService.getAll());
        }
        return modelAndView;
    }

    /**
     * Filtering group's goods
     *
     * @param user
     * @param id
     * @param request
     * @param propertiesIds
     * @param priceFrom
     * @param priceTo
     * @param sortBy
     * @param sortDirection
     * @param limit
     * @param page
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/groups/{id}/goods", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getProductsByGroupId
    (@ActiveUser User user,
     @PathVariable("id") Integer id, HttpServletRequest request,
     @RequestParam(value = "properties", required = false) List<String> propertiesIds,
     @RequestParam(value = "priceFrom", required = false) Double priceFrom,
     @RequestParam(value = "priceTo", required = false) Double priceTo,
     @RequestParam(value = "sortBy", required = false) String sortBy,
     @RequestParam(value = "sortDirection", required = false) String sortDirection,
     @RequestParam(value = "limit", required = false) Integer limit,
     @RequestParam(value = "page", required = false) Integer page,
     HttpSession session) throws Exception {
        ModelAndView modelAndView = getModel("shop/products");
        updateSession(user, session);
        if (id != null) {
            SubcategoryGroup group = groupService.get(id);
            if (group != null) {
                if (page == null || page <= 0) page = 1;
                List<Product> goods = group.getGoodsBySubcategoryGroupId();
                //skip groups filter
                List<SubcategoryGroup> groups = new ArrayList<>();
                List<Property> properties = getPropertiesBySIds(propertiesIds);
                IFilterFacade.ExtendedFiltering extendedFiltering = extendedFilteringProducts(page, limit, goods, properties, groups, priceFrom, priceTo, sortBy, sortDirection);
                goods = extendedFiltering.getGoods();

                addPriceRangeToModel(goods, modelAndView);

                //adding attributes for initializing header form
                addHeaderFormAttributes(sortBy, sortDirection, limit, page, extendedFiltering.getTotal(), modelAndView);

                modelAndView.addObject("goods", goods);
                modelAndView.addObject("category", group.getSubcategoryBySubcategoryId().getCategoryByCategoryId());
                modelAndView.addObject("subcategory", group.getSubcategoryBySubcategoryId());
                modelAndView.addObject("group", group);
                Map<Integer, PropertyClass> propertyClassMap = retrieveRelatedProperties(goods, properties);
                modelAndView.addObject("propertyClassMap", propertyClassMap);
            }
            modelAndView.addObject("categories", categoryService.getAll());
        }
        return modelAndView;
    }

    //endregion


    //region Subcategories

    /**
     * Go to subcategories
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/categories/{id}/subcategories", method = RequestMethod.GET)
    public ModelAndView getSubcategories(@PathVariable("id") String id) {
        ModelAndView modelAndView = getModel("shop/hierarchyItems");
        Integer categoryId = getIdFromString(id);
        if (null != categoryId) {
            Category category = categoryService.get(categoryId);
            modelAndView.addObject("parent", "Home");
            modelAndView.addObject("parentHref", "/");
            modelAndView.addObject("currentParentItem", category);
            modelAndView.addObject("contentType", "Subcategory");
            modelAndView.addObject("title", "Categories");
            modelAndView.addObject("hrefPrefixAside", "/categories/");
            modelAndView.addObject("hrefSuffixAside", "/subcategories");
            modelAndView.addObject("hrefParentPrefix", "/subcategories/");
            modelAndView.addObject("hrefProductSuffix", "/goods");
            modelAndView.addObject("hrefChildSuffix", "/groups");
            List<Category> categoryList = categoryService.getAll();
            modelAndView.addObject("asideItems", categoryList);
            modelAndView.addObject("categories", categoryList);
            modelAndView.addObject("items", category.getSubcategoriesByCategoryId());
        }
        return modelAndView;
    }
    //endregion

    //region Groups

    /**
     * Go to groups
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/subcategories/{id}/groups", method = RequestMethod.GET)
    public ModelAndView getSubcategoryGroups(@PathVariable("id") String id) {
        ModelAndView modelAndView = getModel("shop/hierarchyItems");
        Integer subcategoryId = getIdFromString(id);
        if (null != subcategoryId) {
            Subcategory subcategory = subcategoryService.get(subcategoryId);

            modelAndView.addObject("parent", subcategory.getCategoryByCategoryId().getTitle());
            modelAndView.addObject("parentHref", "categories/" + subcategory.getCategoryByCategoryId().getId() + "/subcategories");
            modelAndView.addObject("currentParentItem", subcategory);
            modelAndView.addObject("contentType", "Group");
            modelAndView.addObject("title", "Subcategories");
            modelAndView.addObject("hrefPrefixAside", "/subcategories/");
            modelAndView.addObject("hrefSuffixAside", "/groups");
            modelAndView.addObject("hrefParentPrefix", "/groups/");
            modelAndView.addObject("hrefProductSuffix", "/goods");
            modelAndView.addObject("hrefChildSuffix", "/goods");
            List<Subcategory> subcategories = subcategory.getCategoryByCategoryId().getSubcategoriesByCategoryId();
            modelAndView.addObject("asideItems", subcategories);
            modelAndView.addObject("categories", categoryService.getAll());
            modelAndView.addObject("items", subcategory.getSubcategoryGroupsBySubcategoryId());
        }
        return modelAndView;
    }
    //endregion

}
