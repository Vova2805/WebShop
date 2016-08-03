package com.webshop.dao.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Category;


public interface ICategoryDAO extends IGenericDAO<Category, Integer> {

    /**
     * Checking if category already exists
     *
     * @param category
     * @return
     */
    boolean alreadyExists(Category category);
}
