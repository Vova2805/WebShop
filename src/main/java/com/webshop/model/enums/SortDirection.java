package com.webshop.model.enums;

/**
 * Count allowed options for sorting
 */
public enum SortDirection {
    ASC,
    DESC;

    public static SortDirection getByName(String name) {
        if (null != name) {
            for (SortDirection sortDirection : values()) {
                if (sortDirection.toString().equals(name.toLowerCase())) {
                    return sortDirection;
                }
            }
        }
        return DESC;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
