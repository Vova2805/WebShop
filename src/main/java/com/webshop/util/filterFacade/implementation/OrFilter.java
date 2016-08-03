package com.webshop.util.filterFacade.implementation;

import com.webshop.model.classes.Product;
import com.webshop.util.filterFacade.interfaces.IFilter;

import java.util.List;

/**
 * Merge two filter results OR
 */
public class OrFilter implements IFilter {

    private IFilter firstFilter;
    private IFilter secondFilter;

    public OrFilter(IFilter firstFilter, IFilter secondFilter) {
        this.firstFilter = firstFilter;
        this.secondFilter = secondFilter;
    }

    @Override
    public List<Product> meetCriteria(List<Product> goods) {
        List<Product> firstFilterItems = firstFilter.meetCriteria(goods);
        List<Product> secondFilterItems = secondFilter.meetCriteria(goods);
        firstFilterItems = IFilter.uniteOr(firstFilterItems, secondFilterItems);
        return firstFilterItems;
    }
}
