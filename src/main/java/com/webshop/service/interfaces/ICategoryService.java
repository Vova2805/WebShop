package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Category;
import com.webshop.model.classes.Product;

import java.util.List;
import java.util.Map;


public interface ICategoryService extends IService<IGenericDAO<Category, Integer>, Category, Integer> {

    List<Category> getCategoryByName(String categoryName);

    Map<Category, List<Product>> getTopCategoryProductsMap(List<Category> categories, int limit);

}
