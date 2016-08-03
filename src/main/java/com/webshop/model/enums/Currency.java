package com.webshop.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Map currency name and symbol
 */
public enum Currency {
    EUR,
    USD,
    UAH;

    private static Map<String, String> symbolMap = new HashMap<String, String>() {{
        put(EUR.toString(), "€");
        put(USD.toString(), "$");
        put(UAH.toString(), "₴");
    }};

    public String toString() {
        return super.toString().toUpperCase();
    }

    public String getSymbol() {
        return symbolMap.get(this.toString());
    }
}
