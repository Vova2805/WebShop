package com.webshop.util.filterFacade.implementation;

import com.webshop.model.classes.Product;
import com.webshop.model.classes.Property;
import com.webshop.model.classes.SubcategoryGroup;
import com.webshop.model.enums.SortDirection;
import com.webshop.model.enums.SortProductBy;
import com.webshop.util.filterFacade.interfaces.IFilter;
import com.webshop.util.filterFacade.interfaces.IFilterFacade;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IFilterFacade implementation
 */
@Component
public class FilterFacade implements IFilterFacade {

    private List<IFilter> groupsFilters;
    private List<IFilter> propertiesFilters;

    /**
     * Initializing list of filters by groups and properties instances
     *
     * @param groups
     */
    private void initGroupsFilters(List<SubcategoryGroup> groups) {
        groupsFilters = new ArrayList<>();
        groups.forEach(group -> {
            groupsFilters.add(new GroupFilter(group));
        });
    }

    private void initPropertiesFilters(List<Property> properties) {
        propertiesFilters = new ArrayList<>();
        properties.forEach(property -> {
            propertiesFilters.add(new PropertyFilter(property));
        });
    }

    /**
     * Call each filter step by step and getting filtered Collection
     *
     * @param goods
     * @param filters
     * @return
     */
    private List<Product> getOrListResult(List<Product> goods, List<IFilter> filters) {
        if (filters.size() == 0) return goods;
        List<Product> resultProducts = new ArrayList<>();
        for (IFilter filter : filters) {
            List<Product> products = filter.meetCriteria(goods);
            IFilter.uniteOr(resultProducts, products);
        }
        return resultProducts;
    }

    /**
     * Different filter methods implementation.
     * Overloading.
     * If parameters are null - they are skipped
     *
     * @param goods
     * @param groups
     * @param properties
     * @param priceFrom
     * @param priceTo
     * @return
     */
    @Override
    public List<Product> filter(List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo) {
        //if groups is empty - length == 0 then we get all subcategory goods
        initGroupsFilters(groups);
        initPropertiesFilters(properties);
        IFilter priceFilter = new PriceFilter(priceFrom, priceTo);
        List<Product> result = goods;
        result = IFilter
                .uniteAnd(
                        getOrListResult(goods, groupsFilters),
                        getOrListResult(goods, propertiesFilters)
                );
        result = IFilter
                .uniteAnd(
                        result,
                        priceFilter.meetCriteria(goods)
                );
        return result;
    }

    @Override
    public List<Product> filter(List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo, Integer limit, Integer offset) {
        List<Product> result = filter(goods, groups, properties, priceFrom, priceTo);
        result = limit(result, limit, offset);
        return result;
    }

    /**
     * Restrict results
     *
     * @param goods
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public List<Product> limit(List<Product> goods, Integer limit, Integer offset) {
        List<Product> result = goods;
        if (offset == null) offset = 0;
        if (limit != null && limit != 0) {
            if (offset >= result.size()) {
                result.clear();
                return result;
            } else {
                result = result.subList(offset, (offset + limit) <= result.size() ? offset + limit : result.size());
            }
        }
        return result;
    }

    @Override
    public List<Product> filter(List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo, Integer limit, Integer offset, SortProductBy sortProductBy, SortDirection sortDirection) {
        return sort(filter(goods, groups, properties, priceFrom, priceTo, limit, offset), sortProductBy, sortDirection);
    }

    /**
     * Sorting results (goods)
     *
     * @param goods
     * @param sortProductBy
     * @param sortDirection
     * @return
     */
    @Override
    public List<Product> sort(List<Product> goods, SortProductBy sortProductBy, SortDirection sortDirection) {
        if (sortDirection.equals(SortDirection.DESC)) {
            Collections.sort(goods, Collections.reverseOrder(sortProductBy));
            return goods;
        }
        Collections.sort(goods, sortProductBy);
        return goods;
    }

    /**
     * Advance filters are used for prediction and for paging
     *
     * @param page
     * @param limit
     * @param goods
     * @param groups
     * @param properties
     * @param priceFrom
     * @param priceTo
     * @return
     */
    @Override
    public ExtendedFiltering advancedFilter(Integer page, Integer limit, List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo) {
        int total = 0;
        if (page == null || page == 0) page = 1;

        if (limit == null) limit = 0;
        List<Product> products = filter(goods, groups, properties, priceFrom, priceTo);
        total = products.size();
        products = limit(products, limit, (page - 1) * limit);

        return new ExtendedFiltering(total, limit, page, products);
    }

    @Override
    public ExtendedFiltering advancedFilter(Integer page, Integer limit, List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo, SortProductBy sortProductBy, SortDirection sortDirection) {
        ExtendedFiltering extendedFiltering = advancedFilter(page, limit, goods, groups, properties, priceFrom, priceTo);
        extendedFiltering.setGoods(sort(extendedFiltering.getGoods(), sortProductBy, sortDirection));
        return extendedFiltering;
    }
}
