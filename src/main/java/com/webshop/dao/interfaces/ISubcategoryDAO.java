package com.webshop.dao.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Subcategory;


public interface ISubcategoryDAO extends IGenericDAO<Subcategory, Integer> {

    Subcategory getSubcategoryByName(String name);
}
