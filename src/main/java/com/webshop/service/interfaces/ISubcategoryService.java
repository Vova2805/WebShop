package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Subcategory;

import java.util.List;


public interface ISubcategoryService extends IService<IGenericDAO<Subcategory, Integer>, Subcategory, Integer> {

    //    @Secured("ROLE_ADMIN")
    List<Subcategory> getSubcategoryByName(String subcategoryName);
}
