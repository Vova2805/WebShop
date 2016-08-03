package com.webshop.controller.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webshop.model.classes.*;
import com.webshop.model.classes.extra.HibernateProxyTypeAdapter;
import com.webshop.model.enums.Currency;
import com.webshop.model.enums.SortDirection;
import com.webshop.model.enums.SortProductBy;
import com.webshop.model.enums.UserRole;
import com.webshop.service.interfaces.ICustomerService;
import com.webshop.service.interfaces.IGroupService;
import com.webshop.service.interfaces.IPropertyClassService;
import com.webshop.service.interfaces.IPropertyService;
import com.webshop.util.filterFacade.interfaces.IFilterFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Contains general methods-helpers and data
 */
public class CommonController {

    //initialize for each active (derived) controller
    protected final Logger logger = Logger.getLogger(this.getClass());

    //region Services and variables
    @Autowired
    protected IFilterFacade filterFacade;
    @Autowired
    protected IGroupService groupService;
    @Autowired
    protected IPropertyClassService propertyClassService;
    @Autowired
    protected IPropertyService propertyService;
    @Autowired
    protected ICustomerService customerService;
    private String currentCurrency = Currency.UAH.getSymbol();
    private HttpServletRequest request;
    //endregion

    //region Getters Setters
    public String getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(Currency currentCurrency) {
        this.currentCurrency = currentCurrency.getSymbol();
    }
    //endregion

    /**
     * Return new viewmodel view with viewName
     *
     * @param viewName
     * @return
     */
    protected ModelAndView getModel(String viewName) {
        ModelAndView model = new ModelAndView();
        model.setViewName(viewName);
        return model;
    }

    /**
     * Convert every object to JSON notation
     * Exclude all fields which are not marked as @Expose
     *
     * @param obj
     * @return
     */
    protected String toGSON(Object obj) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        String result = gson.toJson(obj);
        return result;
    }

    /**
     * Convert string to Integer and handling parse error
     *
     * @param id
     * @return
     */
    protected Integer getIdFromString(String id) {
        Integer objectId = null;
        if (!id.equals("")) {
            try {
                objectId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                logger.error("Error during parseInt. Converting from String to Integer failed");
            }
        }
        return objectId;
    }

    /**
     * Convert String array to Integer array.
     *
     * @param strings
     * @return
     */
    protected List<Integer> getIntsFromStrings(List<String> strings) {
        List<Integer> result = new ArrayList<>();
        strings.forEach(string -> {
            result.add(getIdFromString(string));
        });
        return result;
    }

    /**
     * General method for accessing filter logic. Return object ExtendedFiltering
     *
     * @param page          - implements paging.
     * @param limit         - max number of product in one page
     * @param goods         -list of products
     * @param properties    - list of properties for filtering
     * @param groups        - list of groups for filtering
     * @param priceFrom     - price range
     * @param priceTo
     * @param sortBy        - sort options
     * @param sortDirection
     * @return
     */
    protected IFilterFacade.ExtendedFiltering extendedFilteringProducts(Integer page, Integer limit, List<Product> goods, List<Property> properties, List<SubcategoryGroup> groups, Double priceFrom, Double priceTo, String sortBy, String sortDirection) {

        if (sortBy != null || sortDirection != null) {
            SortDirection direction = SortDirection.getByName(sortDirection);
            SortProductBy sortProductBy = SortProductBy.getByName(sortBy);
            return filterFacade.advancedFilter(page, limit, goods, groups, properties, priceFrom, priceTo, sortProductBy, direction);
        } else {
            //return all (or limited) not ordered
            return filterFacade.advancedFilter(page, limit, goods, groups, properties, priceFrom, priceTo);
        }
    }

    /**
     * Simple filter. Return only list of retrieving products
     *
     * @param goods
     * @param properties
     * @param groups
     * @param priceFrom
     * @param priceTo
     * @param sortBy
     * @param sortDirection
     * @param limit
     * @param offset
     * @return
     */
    protected List<Product> filteringProducts(List<Product> goods, List<Property> properties, List<SubcategoryGroup> groups, Double priceFrom, Double priceTo, String sortBy, String sortDirection, Integer limit, Integer offset) {
        //if groups and properties filter is empty then all products will be returned

        if (sortBy != null || sortDirection != null) {
            SortProductBy sortProductBy = SortProductBy.getByName(sortBy);
            SortDirection direction = SortDirection.getByName(sortDirection);
            return filterFacade.filter(goods, groups, properties, priceFrom, priceTo, limit, offset, sortProductBy, direction);
        } else {
            //return all (or limited) not ordered
            return filterFacade.filter(goods, groups, properties, priceFrom, priceTo, limit, offset);
        }
    }

    /**
     * Converting List<String> which contains properties Ids from request string to real property objects
     *
     * @param propertiesIds
     * @return List<Property>
     */
    protected List<Property> getPropertiesBySIds(List<String> propertiesIds) {
        List<Property> properties = new ArrayList<>();
        if (propertiesIds != null && propertiesIds.size() > 0) {
            getIntsFromStrings(propertiesIds)
                    .forEach(propertyId -> {
                        properties.add(propertyService.get(propertyId));
                    });
        }
        return properties;
    }

    /**
     * Return List of Groups by their id (in string)
     *
     * @param groupsIds
     * @return
     */
    protected List<SubcategoryGroup> getGroupsBySIds(List<String> groupsIds) {
        List<SubcategoryGroup> groups = new ArrayList<>();
        if (groupsIds != null && groupsIds.size() > 0) {
            getIntsFromStrings(groupsIds)
                    .forEach(groupId -> {
                        groups.add(groupService.get(groupId));
                    });
        }
        return groups;
    }

    /**
     * Return all related properties for products.
     * Checked properties - which user check for filtering.
     *
     * @param products
     * @param checkedProperties
     * @return
     */
    protected Map<Integer, PropertyClass> retrieveRelatedProperties(List<Product> products, List<Property> checkedProperties) {
        Map<Integer, PropertyClass> resultMap = new HashMap<>();

        checkedProperties.forEach(property -> {
            PropertyClass propertyClass = property.getPropertyClassByPropertyClassId();
            propertyClass.initFlags();
            //checked by User
            property.setChecked(true);
            //active - related to products
            //all the rest is disabled (within property class)
            property.setActive(true);
            resultMap.put(propertyClass.getPropertyClassId(), propertyClass);
        });

        products.forEach(product -> {
            product.getPropertyProductsByProductId().forEach(property -> {
                if (!resultMap.containsKey(property.getPropertyClassByPropertyClassId().getPropertyClassId())) {
                    PropertyClass propertyClass = property.getPropertyClassByPropertyClassId();
                    propertyClass.initFlags();
                    resultMap.put(property.getPropertyClassByPropertyClassId().getPropertyClassId(), propertyClass);

                    property.setActive(true);
                } else {
                    Property innerProperty = resultMap.get(
                            property.getPropertyClassId())
                            .getPropertiesByPropertyClassId()
                            .stream()
                            .filter(pr -> pr.getPropertyId() == property.getPropertyId())
                            .findFirst()
                            .get();
                    innerProperty.setActive(true);
                }
            });
        });
        return resultMap;
    }

    /**
     * Calculating maximum and minimum price occurrences
     *
     * @param products
     * @param modelAndView
     */
    protected void addPriceRangeToModel(List<Product> products, ModelAndView modelAndView) {
        Integer to = 0;

        for (Product product : products) {
            if (product.getPrice() > to)
                to = (int) Math.ceil(product.getPrice());
        }
        modelAndView.addObject("priceTo", to);
    }

    /**
     * Initialize header attributes default values and changed between requests
     *
     * @param sortBy
     * @param sortDirection
     * @param limit
     * @param page
     * @param total
     * @param modelAndView
     */
    protected void addHeaderFormAttributes(String sortBy, String sortDirection, Integer limit, Integer page, Integer total, ModelAndView modelAndView) {
        if (limit == null) limit = 0;
        if (total == null) total = 0;
        if (page == null) page = 0;
        if (page == 0) page = 1;
        modelAndView.addObject("sortBy", sortBy);
        modelAndView.addObject("sortDirection", sortDirection);
        modelAndView.addObject("limit", limit);
        modelAndView.addObject("page", page);
        modelAndView.addObject("pageCount", (limit > 0) ? ((total / limit)) : 0);

        List<String> items = new ArrayList<>();
        for (SortProductBy sortProductBy : SortProductBy.values()) {
            items.add(sortProductBy.toString());
        }
        modelAndView.addObject("sortByItems", items);
        items = new ArrayList<>();
        for (SortDirection direction : SortDirection.values()) {
            items.add(direction.toString());
        }
        modelAndView.addObject("sortDirectionItems", items);
        modelAndView.addObject("limitItems", new ArrayList<String>() {{
            add("4");
            add("12");
            add("24");
            add("36");
        }});
    }

    /**
     * Retrieving userId from sessiong and returning DB instance
     *
     * @param session
     * @return
     */
    protected Customer getActiveUser(HttpSession session) {
        Integer userId = getIdFromString(session.getAttribute("activeUserId").toString());
        return customerService.get(userId);
    }

    /**
     * Updating session suring some requsts
     *
     * @param user
     * @param session
     */
    protected void updateSession(User user, HttpSession session) {
        session.setAttribute("currency", getCurrentCurrency());
        if (user != null) {
            Customer customer = customerService.getCustomer(user.getUsername()).get(0);
            if (customer != null) {
                updateSession(customer, session);
            }
        }
    }

    /**
     * If we got original user from DB
     *
     * @param customer
     * @param session
     */
    protected void updateSession(Customer customer, HttpSession session) {
        session.setAttribute("activeUserId", customer.getCustomerId());
        session.setAttribute("activeUser", customer);
        session.setAttribute("currency", getCurrentCurrency());
        if (customer.getRoleByRoleId().getRole().equals(UserRole.USER.toString())) {
            List<Cart> carts = customer.getCartsByCustomerId();
            carts.forEach(cart -> {
                cart.getProductByProductId().initializeImages();
            });
            Collections.reverse(carts);
            session.setAttribute("menuCarts", carts.subList(Math.max(carts.size() - 5, 0), carts.size()));
            List<Comparison> comparisons = customer.getComparisonsByCustomerId();
            comparisons.forEach(comparison -> {
                comparison.getProductByProductId().initializeImages();
            });
            Collections.reverse(comparisons);
            session.setAttribute("menuComparisons", comparisons.subList(Math.max(comparisons.size() - 5, 0), comparisons.size()));
            List<Wish> wishes = customer.getWishesByCustomerId();
            wishes.forEach(wishe -> {
                wishe.getProductByProductId().initializeImages();
            });
            Collections.reverse(wishes);
            session.setAttribute("menuWishes", wishes.subList(Math.max(wishes.size() - 5, 0), wishes.size()));
        }
    }

    /**
     * Write image paths to modelAndView
     *
     * @param modelAndView
     * @param product
     */
    protected void initializeProductImages(ModelAndView modelAndView, Product product) {
        List<Image> images = product.getImagesByProductId();
        modelAndView.addObject("images", images);
    }

    /**
     * Converting single element to JSON
     *
     * @param variableTitle
     * @param value
     * @return
     */
    protected String toGSON(String variableTitle, String value) {
        return "{\"" + variableTitle + "\":" + value + "}";
    }

}
