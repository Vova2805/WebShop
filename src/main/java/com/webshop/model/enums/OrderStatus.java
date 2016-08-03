package com.webshop.model.enums;

/**
 * Define allowed order status
 */
public enum OrderStatus {
    SHIPPED("primary"),
    PROCESSING("info"),
    DELIVERED("success"),
    CANCELED("danger");

    private String label;

    OrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        String result = super.toString().toLowerCase();
        result.substring(0, 1).toUpperCase();
        return result;
    }
}
