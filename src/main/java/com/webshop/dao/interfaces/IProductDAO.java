package com.webshop.dao.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Category;
import com.webshop.model.classes.Product;
import com.webshop.model.classes.Subcategory;
import com.webshop.model.classes.SubcategoryGroup;

import java.util.List;

public interface IProductDAO extends IGenericDAO<Product, Integer> {

    /**
     * Return limit products with higher rating
     *
     * @param limit
     * @param offset
     * @return
     */
    List<Product> getPopular(int limit, int offset);

    /**
     * Return newest added products
     *
     * @param limit
     * @param offset
     * @return
     */
    List<Product> getNewest(int limit, int offset);

    List<Product> getProductsByCategory(Category category);

    List<Product> getProductsBySubcategory(Subcategory subcategory);

    List<Product> getProductsByGroup(SubcategoryGroup subcategoryGroup);

    List<Product> getProductsByCategory(Category category, int limit, int offset);

    List<Product> getProductsBySubcategory(Subcategory subcategory, int limit, int offset);

    List<Product> getProductsByGroup(SubcategoryGroup subcategoryGroup, int limit, int offset);

    List<Product> getLatest(int limit);

}
