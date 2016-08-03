package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.PropertyClass;

import java.util.List;


public interface IPropertyClassService extends IService<IGenericDAO<PropertyClass, Integer>, PropertyClass, Integer> {

    List<PropertyClass> getPropertyClassByTitle(String title);
}
