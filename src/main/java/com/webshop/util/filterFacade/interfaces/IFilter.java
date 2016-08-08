package com.webshop.util.filterFacade.interfaces;

import com.webshop.model.classes.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IFilter {

    /**
     * Static method for merging two sets (lists) with or option
     *
     * @param goods1
     * @param goods2
     * @return
     */
    static List<Product> uniteOr(List<Product> goods1, List<Product> goods2) {
        for (Product product : goods2) {
            if (!goods1.contains(product)) {
                goods1.add(product);
            }
        }
        return goods1;
    }

    /**
     * Static method for merging two sets (lists) with and option
     *
     * @param goods1
     * @param goods2
     * @return
     */
    static List<Product> uniteAnd(List<Product> goods1, List<Product> goods2) {
        List<Product> result = new ArrayList<>();
        goods1.forEach(product -> {
                    Optional<Product> optional = goods2
                            .stream().filter(
                                    good -> good.getProductId() == product.getProductId()
                            ).findAny();
                    //if product contains property with property.getPropertyId() Identifier
                    if (optional.isPresent()) {
                        result.add(product);
                    }
                }
        );
        return result;
    }

    /**
     * Main method for filtering goods
     *
     * @param goods
     * @return
     */
    List<Product> meetCriteria(List<Product> goods);
}
