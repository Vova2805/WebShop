package com.webshop.model.enums;

import com.webshop.model.classes.Product;

import java.util.Comparator;

/**
 * Sorting product by
 */
public enum SortProductBy implements Comparator<Product> {

    /**
     * Define comparators for ascending sort
     */
    RATE {
        @Override
        public int compare(Product product1, Product product2) {
            return ((Double) product1.getRating()).compareTo(product2.getRating());
        }
    },//default
    TITLE {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.getTitle().compareToIgnoreCase(product2.getTitle());
        }
    },
    DATE {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.getAddedDate().compareTo(product2.getAddedDate());
        }
    },
    PRICE {
        @Override
        public int compare(Product product1, Product product2) {
            return ((Double) product1.getPrice()).compareTo(product2.getPrice());
        }
    };

    public static SortProductBy getByName(String name) {
        if (null != name) {
            for (SortProductBy sortProductBy : values()) {
                if (sortProductBy.toString().equals(name.toLowerCase())) {
                    return sortProductBy;
                }
            }
        }
        return TITLE;
    }

    @Override
    public String toString() {
        String string = super.toString();//Name
        return string.toLowerCase();
    }
}
