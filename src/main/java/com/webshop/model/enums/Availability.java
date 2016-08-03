package com.webshop.model.enums;

/**
 * Define product availability options
 */
public enum Availability {
    IN_STOCK("In stock"),
    ENDS("Ends"),
    INACCESSIBLE("Inaccessible");

    private String title;

    Availability(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
