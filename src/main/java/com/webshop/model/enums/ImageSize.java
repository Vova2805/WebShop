package com.webshop.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Define all accessible image sizes to avoid hard code in many places
 */
public enum ImageSize {
    /**
     * Advertisement 1920*700
     */
    HUGE,
    /**
     * Product large preview 900*1024
     */
    LARGE,
    /**
     * Product preview 250*300
     */
    MEDIUM,
    /**
     * Product small preview 45*55
     */
    SMALL,
    /**
     * Avatar,category,subcategory,group preview 128*128
     */
    STANDART_128,
    /**
     * Small avatar preview 30*30
     */
    AVATAR_SMALL;

    private static Map<String, Size> sizeMap = new HashMap<String, Size>() {{
        put(HUGE.toString(), new Size(1920, 700));
        put(LARGE.toString(), new Size(900, 1024));
        put(MEDIUM.toString(), new Size(250, 300));
        put(SMALL.toString(), new Size(45, 55));
        put(STANDART_128.toString(), new Size(128, 128));
        put(AVATAR_SMALL.toString(), new Size(30, 30));
    }};

    public Size getSize() {
        return sizeMap.get(this.toString());
    }

    public int getWidth() {
        return getSize().getWidth();
    }

    public int getHeight() {
        return getSize().getHeight();
    }

    public String toString() {
        return super.toString().toLowerCase();
    }

    public static class Size {
        private int height;
        private int width;

        public Size(int width, int height) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
