package com.webshop.util.filterFacade.interfaces;

import com.google.gson.annotations.Expose;
import com.webshop.model.classes.Product;
import com.webshop.model.classes.Property;
import com.webshop.model.classes.SubcategoryGroup;
import com.webshop.model.enums.SortDirection;
import com.webshop.model.enums.SortProductBy;

import java.util.ArrayList;
import java.util.List;

public interface IFilterFacade {

    /**
     * Returning object with advanced info for filtering and prediction
     *
     * @param page
     * @param limit
     * @param goods
     * @param groups
     * @param properties
     * @param priceFrom
     * @param priceTo
     * @return
     */
    ExtendedFiltering advancedFilter(Integer page, Integer limit, List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo);

    ExtendedFiltering advancedFilter(Integer page, Integer limit, List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo, SortProductBy sortProductBy, SortDirection sortDirection);

    /**
     * Methods for simple filtering with different options
     *
     * @param goods
     * @param groups
     * @param properties
     * @param priceFrom
     * @param priceTo
     * @return
     */
    List<Product> filter(List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo);

    List<Product> filter(List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo, Integer limit, Integer offset);

    List<Product> filter(List<Product> goods, List<SubcategoryGroup> groups, List<Property> properties, Double priceFrom, Double priceTo, Integer limit, Integer offset, SortProductBy sortProductBy, SortDirection sortDirection);

    List<Product> sort(List<Product> goods, SortProductBy sortProductBy, SortDirection sortDirection);

    List<Product> limit(List<Product> goods, Integer limit, Integer offset);

    /**
     * Filtering result box
     */

    class ExtendedFiltering {
        @Expose
        private int total;
        @Expose
        private int limit;
        @Expose
        private int page;
        @Expose
        private List<Product> goods;

        public ExtendedFiltering() {
            total = 0;
            limit = 0;
            page = 0;
            goods = new ArrayList<>();
        }

        public ExtendedFiltering(int total, int limit, int page, List<Product> goods) {
            this.total = total;
            this.limit = limit;
            this.page = page;
            this.goods = goods;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<Product> getGoods() {
            return goods;
        }

        public void setGoods(List<Product> goods) {
            this.goods = goods;
        }
    }
}
