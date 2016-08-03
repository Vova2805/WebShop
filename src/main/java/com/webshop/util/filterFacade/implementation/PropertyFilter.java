package com.webshop.util.filterFacade.implementation;

import com.webshop.model.classes.Product;
import com.webshop.model.classes.Property;
import com.webshop.util.filterFacade.interfaces.IFilter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Filter product by their properties
 */
public class PropertyFilter implements IFilter {

    private Property property;

    public PropertyFilter(Property property) {
        this.property = property;
    }

    @Override
    public List<Product> meetCriteria(List<Product> goods) {
        List<Product> products = goods
                .stream()
                .filter(product -> {
                            List<Property> list = product.getPropertyProductsByProductId();
                            Optional<Property> optional = list
                                    .stream().filter(
                                            property1 ->
                                                    property1.getPropertyId() == property.getPropertyId()
                                    ).findAny();
                            //if product contains property with property.getPropertyId() Identifier
                    return optional.isPresent();
                        }
                )
                .collect(Collectors.toList());
        return products;
    }
}