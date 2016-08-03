package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Category;
import com.webshop.model.classes.Product;
import com.webshop.model.classes.Subcategory;
import com.webshop.model.classes.SubcategoryGroup;

import java.util.List;
import java.util.Map;


public interface IProductService extends IService<IGenericDAO<Product, Integer>, Product, Integer> {

    //filters
    List<Product> getPopular(int limit, int offset);

    List<Product> getNewest(int limit, int offset);

    Map<String, List<Product>> getPopularNewestMap(int limit);

    List<Product> getProductsByCategory(Category category);

    List<Product> getProductsBySubcategory(Subcategory subcategory);

    List<Product> getProductsByGroup(SubcategoryGroup subcategoryGroup);

    List<Product> getProductsByCategory(Category category, int limit, int offset);

    List<Product> getProductsBySubcategory(Subcategory subcategory, int limit, int offset);

    List<Product> getProductsByGroup(SubcategoryGroup subcategoryGroup, int limit, int offset);

    List<Product> getLatest(int limit);
}
