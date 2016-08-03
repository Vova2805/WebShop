package com.webshop.util.filterFacade.implementation;

import com.webshop.model.classes.Product;
import com.webshop.util.filterFacade.interfaces.IFilter;

import java.util.List;

/**
 * Merge result of filtering using && operation
 */
public class AndFilter implements IFilter {

    private IFilter firstFilter;
    private IFilter secondFilter;

    public AndFilter(IFilter firstFilter, IFilter secondFilter) {
        this.firstFilter = firstFilter;
        this.secondFilter = secondFilter;
    }

    @Override
    public List<Product> meetCriteria(List<Product> goods) {
        List<Product> firstFilterItems = firstFilter.meetCriteria(goods);
        List<Product> secondFilterItems = secondFilter.meetCriteria(goods);
        return IFilter.uniteAnd(firstFilterItems, secondFilterItems);
    }
}
