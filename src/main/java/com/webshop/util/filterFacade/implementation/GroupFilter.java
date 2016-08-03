package com.webshop.util.filterFacade.implementation;

import com.webshop.model.classes.Product;
import com.webshop.model.classes.SubcategoryGroup;
import com.webshop.util.filterFacade.interfaces.IFilter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Filter by groups
 */
public class GroupFilter implements IFilter {

    private SubcategoryGroup subcategoryGroup;

    public GroupFilter(SubcategoryGroup subcategoryGroup) {
        this.subcategoryGroup = subcategoryGroup;
    }

    @Override
    public List<Product> meetCriteria(List<Product> goods) {
        return goods
                .stream()
                .filter(product -> {
                    return product.getSubcategoryGroupBySubcategoryGroupId().getId() == subcategoryGroup.getId();
                })
                .collect(Collectors.toList());
    }
}
