package com.webshop.controller.ui;

import com.webshop.anotation.ActiveUser;
import com.webshop.controller.base.CommonController;
import com.webshop.model.classes.*;
import com.webshop.model.classes.extra.ImageDecorator;
import com.webshop.model.classes.extra.UploadedImage;
import com.webshop.model.enums.ImageSize;
import com.webshop.model.enums.OrderStatus;
import com.webshop.model.enums.UserRole;
import com.webshop.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController extends CommonController {

    //region Services
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ISubcategoryService subcategoryService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IPropertyClassService propertyClassService;
    @Autowired
    private IImageService imageService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAdvertisementService advertisementService;
    //endregion

    //region Admin home

    private ModelAndView initModelAndView() {
        ModelAndView modelAndView = getModel("admin/adminHome");
        return modelAndView;
    }

    /**
     * Admin panel request. Accessible only for ADMIN
     *
     * @param user
     * @param session
     * @param block
     * @param type
     * @param itemId
     * @param request
     * @return
     */
    @Secured({"ADMIN", "IS_AUTHENTICATED_FULLY"})
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPanel(@ActiveUser User user, HttpSession session,
                                   @RequestParam(value = "block", required = false) String block,
                                   @RequestParam(value = "type", required = false) String type,
                                   @RequestParam(value = "id", required = false) String itemId,
                                   HttpServletRequest request) {
        if (customerService.getCustomer(user.getUsername()) != null) {
            if (user != null) {
                Customer customer = customerService.getCustomer("admin").get(0);
                if (customer != null) {
                    updateSession(customer, session);
                    if (block != null) {
                        switch (block) {
                            //Changing block content depend on request parameters
                            case "profile":
                                return adminProfile(customer, request);
                            case "full":
                                return adminFull(type, itemId, request);
                            case "advertisements":
                                return adminAdvertisement(request);
                            case "properties":
                                return adminProperties(type, itemId, request);
                            case "users":
                                return adminUsers(request);
                            case "orders":
                                return adminOrders(request);
                        }
                    }
                    return adminDashboard(request);
                }
            }
        }
        return new ModelAndView("login");
    }

    /**
     * Showing dashboard
     *
     * @param request
     * @return
     */
    private ModelAndView adminDashboard(HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        modelAndView.addObject("orders", orderService.getLatest(10));
        modelAndView.addObject("goods", productService.getLatest(10));
        modelAndView.addObject("usersCount", customerService.getUsers(UserRole.USER).size());
        modelAndView.addObject("ordersCount", orderService.count());
        modelAndView.addObject("productsCount", productService.count());
        double revenue = 0;

        for (ProductOrder productOrder : ((List<ProductOrder>) (Object) orderService.getAll())) {
            revenue += productOrder.getTotal();
        }
        modelAndView.addObject("revenueCount", revenue);
        modelAndView.addObject("currency", getCurrentCurrency());
        modelAndView.addObject("page", "partial/dashboard/adminDashboardPartial.jsp");
        return modelAndView;
    }

    /**
     * Main menu for editing all service objects (categories, sub,groups, products)
     *
     * @param type
     * @param itemId
     * @param request
     * @return
     */
    private ModelAndView adminFull(String type, String itemId, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        modelAndView.addObject("page", "partial/content/adminFullContent.jsp");
        modelAndView.addObject("categories", categoryService.getAll());
        if (type != null && itemId != null) {
            Product product = null;
            Integer objectId = getIdFromString(itemId);
            modelAndView.addObject("imageInstance", new UploadedImage());
            switch (type) {
                case "categories": {
                    if (objectId != null) {
                        Category category = categoryService.get(objectId);
                        modelAndView.addObject("objectType", "Category");
                        modelAndView.addObject("object", category);
                        modelAndView.addObject("objectId", category.getId());
                        modelAndView.addObject("level", 0);
                        modelAndView.addObject("type", "categories");
                    }
                }
                break;
                case "subcategories": {
                    if (objectId != null) {
                        Subcategory subcategory = subcategoryService.get(objectId);
                        modelAndView.addObject("objectType", "Subcategory");
                        modelAndView.addObject("object", subcategory);
                        modelAndView.addObject("objectId", subcategory.getId());
                        modelAndView.addObject("category", subcategory.getCategoryByCategoryId());
                        modelAndView.addObject("level", 1);
                        modelAndView.addObject("type", "subcategories");
                    }
                }
                break;
                case "groups": {
                    if (objectId != null) {
                        SubcategoryGroup group = groupService.get(objectId);
                        modelAndView.addObject("objectType", "Group");
                        modelAndView.addObject("object", group);
                        modelAndView.addObject("category", group.getSubcategoryBySubcategoryId().getCategoryByCategoryId());
                        modelAndView.addObject("subcategory", group.getSubcategoryBySubcategoryId());
                        modelAndView.addObject("objectId", group.getId());
                        modelAndView.addObject("level", 2);
                        modelAndView.addObject("type", "groups");
                    }
                }
                break;
                case "goods": {
                    if (objectId != null) {
                        product = productService.get(objectId);
                        modelAndView.addObject("innerPage", "fullContentSingleProduct.jsp");
                        product.initializeImages();
                        initializeProductImages(modelAndView, product);
                        modelAndView.addObject("currentProduct", product);
                        modelAndView.addObject("objectId", product.getProductId());
                        modelAndView.addObject("category", product.getSubcategoryGroupBySubcategoryGroupId().getSubcategoryBySubcategoryId().getCategoryByCategoryId());
                        modelAndView.addObject("subcategory", product.getSubcategoryGroupBySubcategoryGroupId().getSubcategoryBySubcategoryId());
                        modelAndView.addObject("group", product.getSubcategoryGroupBySubcategoryGroupId());
                        modelAndView.addObject("propertyClasses", propertyClassService.getAll());
                        modelAndView.addObject("type", "goods");

                    }
                }
                break;
            }
        }
        return modelAndView;
    }

    //region Subpages Partial

    /**
     * For each option exists partial pages which is included to general (master) page
     *
     * @param request
     * @return
     */
    private ModelAndView adminAdvertisement(HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        final List<Advertisement> advertisements = ((List<Advertisement>) (Object) advertisementService.getAll());
        modelAndView.addObject("advertisements", advertisements);
        modelAndView.addObject("page", "partial/advertisement/adminAdvertisements.jsp");
        return modelAndView;
    }

    private ModelAndView adminProperties(String type, String itemId, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        List<PropertyClass> propertiesClasses = ((List<PropertyClass>) (Object) propertyClassService.getAll());
        modelAndView.addObject("propertiesClasses", propertiesClasses);
        modelAndView.addObject("page", "partial/properties/adminPropertiesPartial.jsp");
        return modelAndView;
    }

    private ModelAndView adminUsers(HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        List<Customer> customers = customerService.getUsers(UserRole.USER);
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("extension_" + ImageSize.STANDART_128, ImageDecorator.getExtension(ImageSize.STANDART_128));
        modelAndView.addObject("page", "partial/users/usersPartial.jsp");
        return modelAndView;
    }

    private ModelAndView adminOrders(HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        List<ProductOrder> orders = orderService.getNotReleased();
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("currency", getCurrentCurrency());
        modelAndView.addObject("page", "partial/orders/ordersPartial.jsp");
        List<String> statuses = new ArrayList<>();

        for (OrderStatus status : OrderStatus.values()) {
            statuses.add(status.toString());
        }
        modelAndView.addObject("orderStatuses", statuses);
        return modelAndView;
    }

    private ModelAndView adminProfile(Customer customer, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        Address address = customer.getAddressByAddressId();
        if (address == null) {
            address = new Address();
        }
        modelAndView.addObject("address", address);
        modelAndView.addObject("page", "partial/profile/adminProfilePartial.jsp");
        return modelAndView;
    }
    //endregion
    //endregion

    //region Categories

    /**
     * Handle AJAX query. Adding new category and return markup with model. Ajax just append response to container
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/full/categories", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addCategory(HttpServletRequest request) {
        ModelAndView modelAndView = getModel("admin/partial/content/treeview/categoryTreeViewItem");
        Category category = new Category("New category");
        category = categoryService.add(category);
        modelAndView.addObject("loopCategory", category);
        return modelAndView;
    }

    /**
     * REmove category and return its id
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/full/categories/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Integer deleteCategory(@PathVariable("id") String id, HttpServletRequest request) {
        Integer categoryId = getIdFromString(id);
        if (null != categoryId) {
            categoryService.delete(categoryId);
        }
        return categoryId;
    }

    //endregion

    //region Subcategories

    /**
     * Create subcategory and adding it to list (return markup)
     *
     * @param categoryId
     * @param request
     * @return
     */
    @RequestMapping(value = "admin/full/subcategories", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addSubcategory(@RequestParam(value = "parrentId", required = true) String categoryId, HttpServletRequest request) {
        ModelAndView modelAndView = getModel("admin/partial/content/treeview/subcategoryTreeViewItem");
        Integer id = getIdFromString(categoryId);
        if (null != id) {
            Category category = categoryService.get(id);
            Subcategory subcategory = new Subcategory(id, "New subcategory", category);
            subcategory = subcategoryService.add(subcategory);
            modelAndView.addObject("loopSubcategory", subcategory);
        }
        return modelAndView;
    }

    /**
     * Deleting subcategory
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "admin/subcategories/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Integer deleteSubcategory(@PathVariable("id") String id, HttpServletRequest request) {
        Integer subcategoryId = getIdFromString(id);
        if (null != subcategoryId) {
            subcategoryService.delete(subcategoryId);
        }
        return subcategoryId;
    }
    //endregion

    //region Groups

    /**
     * Adding groups
     *
     * @param parrentId
     * @param request
     * @return
     */
    @RequestMapping(value = "admin/groups", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addGroup(@RequestParam(value = "parrentId", required = true) String parrentId, HttpServletRequest request) {
        ModelAndView modelAndView = getModel("admin/partial/content/treeview/groupTreeViewItem");
        Integer id = getIdFromString(parrentId);

        if (null != id) {
            Subcategory subcategory = subcategoryService.get(id);
            SubcategoryGroup subcategoryGroup = new SubcategoryGroup(id, "New group", subcategory);
            subcategoryGroup = groupService.add(subcategoryGroup);
            modelAndView.addObject("loopGroup", subcategoryGroup);
        }
        return modelAndView;
    }

    @RequestMapping(value = "admin/groups/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Integer deleteGroup(@PathVariable("id") String id, HttpServletRequest request) {
        Integer groupId = getIdFromString(id);
        if (null != groupId) {
            groupService.delete(groupId);
        }
        return groupId;
    }

    //endregion

    //region Products
    //Basic operation with product

    /**
     * Add new Product
     *
     * @param parrentId
     * @param request
     * @return
     */
    @RequestMapping(value = "admin/goods", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addProduct(@RequestParam(value = "parrentId", required = true) String parrentId, HttpServletRequest request) {
        ModelAndView modelAndView = getModel("admin/partial/content/treeview/productTreeViewItem");
        Integer id = getIdFromString(parrentId);

        if (null != id) {
            SubcategoryGroup subcategoryGroup = groupService.get(id);
            Product product = new Product(id, "New product", subcategoryGroup);
            product = productService.add(product);
            modelAndView.addObject("loopProduct", product);
        }
        return modelAndView;
    }

    /**
     * Deleting product and changing ui
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "admin/goods/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Integer deleteProduct(@PathVariable("id") String id, HttpServletRequest request) {
        Integer productId = getIdFromString(id);
        if (null != productId) {
            productService.delete(productId);
        }
        return productId;
    }

    /**
     * Updating images preview block
     *
     * @param prodIds
     * @param model
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/admin/goods/{productId}/images", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView updateImagesView(@PathVariable("productId") String prodIds,
                                         ModelMap model,
                                         HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = getModel("admin/partial/content/productPreview/productImagesPreview");
        Integer productId = getIdFromString(prodIds);
        if (productId != null) {
            Product product = productService.get(productId);
            modelAndView.addObject("product", product);
            initializeProductImages(modelAndView, product);
        }
        return modelAndView;
    }

    /**
     * Update small product image previews
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/goods/{id}/imagesPreview", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView imagesThumblnails(@PathVariable("id") String id, HttpServletRequest request) {
        Integer productId = getIdFromString(id);
        ModelAndView modelAndView = getModel("shop/partial/smallImagePreviewItem");
        if (null != productId) {
            List<Image> images = imageService.getProductImage(productService.get(productId));
            Image image = images.get(images.size() - 1);
            modelAndView.addObject("image", image);
        }
        return modelAndView;
    }

    /**
     * Return description block
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/goods/{id}/descriptions", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addNewDescriptionPart(@PathVariable("id") String id, HttpServletRequest request) {

        Integer productId = getIdFromString(id);
        ModelAndView modelAndView = getModel("admin/partial/content/descriptionPartPartial");
        logger.info("Creating new product description part");
        if (productId != null) {
            Product product = productService.get(productId);
            DescriptionPart descriptionPart = new DescriptionPart("New description header", "New description body", product);
            product.addDescriptionPart(descriptionPart);
            productService.update(product);
            modelAndView.addObject("product", product);
            modelAndView.addObject("description", descriptionPart);
        }
        return modelAndView;
    }

    /**
     * Add new descriptionPart
     *
     * @param descriptionPart
     * @param id
     */
    @RequestMapping(value = "/admin/goods/{id}/descriptions/{did}", method = RequestMethod.PUT)
    public void changeDescriptionPart(@ModelAttribute("description") DescriptionPart descriptionPart, @PathVariable("id") String id, @PathVariable("did") String did, HttpServletRequest request) {

        Integer productId = getIdFromString(id);
        Integer descriptionId = getIdFromString(did);
        ModelAndView modelAndView = getModel("admin/partial/content/descriptionPartPartial");
        logger.info("Creating new product description part");
        if (productId != null) {
            modelAndView.addObject("description", descriptionPart);
            modelAndView.addObject("imageInstance", new UploadedImage());
        }
    }

    /**
     * Delete description part by id
     *
     * @param productIds
     * @param descriptionIds
     * @return
     */
    @RequestMapping(value = "/admin/goods/{pid}/descriptions/{did}/delete", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView removeDescriptionPart(@PathVariable("pid") String productIds, @PathVariable("did") String descriptionIds) {

        Integer descriptionId = getIdFromString(descriptionIds);
        Integer productId = getIdFromString(productIds);
        ModelAndView modelAndView = getModel("admin/partial/content/productDescriptionsPartial");
        if (productId != null && descriptionId != null) {
            logger.info("Removing description part");
            Product product = productService.get(productId);
            product.removeDescriptionPart(descriptionId);
            productService.update(product);
            modelAndView.addObject("product", product);
        }
        return modelAndView;
    }

    //endregion

    //region Advertisement

    //Basic actions with advertisement

    /**
     * Get all
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"admin/adv"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView adminAdvertisementsContent(HttpServletRequest request) {

        ModelAndView modelAndView = getModel("admin/partial/advertisement/adminAdvertisements");
        modelAndView.addObject("advertisements", advertisementService.getAll());
        return modelAndView;
    }

    /**
     * Get advertisement by Id
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = {"admin/adv/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView adminAdvertisementsContentById(@PathVariable("id") String id, HttpServletRequest request) {

        ModelAndView modelAndView = getModel("admin/partial/advertisement/advertisementItem");
        Integer advertId = getIdFromString(id);
        Advertisement advertisement = null;
        if (advertId != null) {
            advertisement = advertisementService.get(advertId);
        }
        modelAndView.addObject("advertisement", advertisement);
        return modelAndView;
    }
    //endregion

    //region Properties


    /**
     * AJAX response
     * Return partial view and model for property
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/properties/property/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView adminPanelPropertiesInner(@PathVariable("id") String id, HttpServletRequest request) {
        ModelAndView modelAndView = getModel("admin/partial/properties/adminPropertyEditable");
        try {
            Integer Id = Integer.parseInt(id);
            if (null != id) {
                Property property = propertyService.get(Id);
                List<PropertyClass> propertyClasses = propertyClassService.getAll();
                modelAndView.addObject("property", property);
                modelAndView.addObject("propertyClasses", propertyClasses);
            }
        } catch (NumberFormatException e) {
            logger.error("Error during parseInt. AdminController");
        }
        return modelAndView;
    }


    /**
     * Return partial view and model for property class by id
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/properties/class/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView adminPanelPropertyClassContentInner(@PathVariable("id") String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Integer Id = Integer.parseInt(id);
            if (null != id) {
                modelAndView.setViewName("admin/partial/properties/adminPropertyClassEditable");
                PropertyClass propertyClass = propertyClassService.get(Id);
                modelAndView.addObject("propertyClass", propertyClass);
            }
        } catch (NumberFormatException e) {
            logger.error("Error during parseInt. AdminController");
        }
        return modelAndView;
    }

    /**
     * Get all properties
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/properties", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView adminPropertiesContent(HttpServletRequest request) {
        ModelAndView modelAndView = getModel("admin/partial/properties/adminPropertiesTreeview");
        List<PropertyClass> propertiesClasses = ((List<PropertyClass>) (Object) propertyClassService.getAll());
        modelAndView.addObject("propertiesClasses", propertiesClasses);
        return modelAndView;
    }

    /**
     * Add property
     *
     * @param classId
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/properties", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addProperty(@RequestParam(value = "parrentId", required = true) String classId, HttpServletRequest request) {
        ModelAndView modelAndView = getModel("admin/partial/properties/propertyTreeViewItem");
        Integer propertyClassId = getIdFromString(classId);

        if (null != propertyClassId) {
            PropertyClass propertyClass = propertyClassService.get(propertyClassId);
            Property property = new Property(propertyClassId, "default", propertyClass);
            property = propertyService.add(property);
            modelAndView.addObject("loopProperty", property);
            modelAndView.addObject("loopPropertyClass", property.getPropertyClassByPropertyClassId());
        }
        return modelAndView;
    }

    /**
     * Add property class
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/properties/propertyClasses", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addPropertyClass(HttpServletRequest request) {
        ModelAndView modelAndView = getModel("admin/partial/properties/propertyClassTreeViewItem");
        PropertyClass propertyClass = new PropertyClass("New property class", "");
        propertyClass = propertyClassService.add(propertyClass);
        modelAndView.addObject("loopPropertyClass", propertyClass);
        return modelAndView;
    }

    //endregion

    //region Users

    /**
     * Enable / Disable user
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/users/{id}/toggleEnabled", method = RequestMethod.PUT)
    @ResponseBody
    public boolean toggleEnabled(@PathVariable("id") String id,
                                 HttpServletRequest request) {
        Integer userId = getIdFromString(id);
        if (null != userId) {
            return customerService.toggleEnable(userId);
        }
        return true;
    }
    //endregion
}