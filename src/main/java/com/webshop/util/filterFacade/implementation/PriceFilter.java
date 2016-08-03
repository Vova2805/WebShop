package com.webshop.util.filterFacade.implementation;

import com.webshop.model.classes.Product;
import com.webshop.util.filterFacade.interfaces.IFilter;

import java.util.List;
import java.util.stream.Collectors;

public class PriceFilter implements IFilter {

    private Double priceFrom;
    private Double priceTo;

    public PriceFilter(Double priceFrom, Double priceTo) {
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    @Override
    public List<Product> meetCriteria(List<Product> goods) {
        List<Product> graterThen = goods;
        List<Product> lessThen = goods;
        if (priceFrom == null && priceTo == null) return goods;
        if (priceTo == 0.0 && priceFrom == 0.0) return goods;

        if (priceFrom != null) {
            graterThen = goods
                    .stream()
                    .filter(product -> {
                        return product.getPrice() >= priceFrom;
                    })
                    .collect(Collectors.toList());
        }

        if (priceTo != null) {
            lessThen = goods
                    .stream()
                    .filter(product -> {
                        return product.getPrice() <= priceTo;
                    })
                    .collect(Collectors.toList());
        }
        return IFilter.uniteAnd(graterThen, lessThen);
    }
}