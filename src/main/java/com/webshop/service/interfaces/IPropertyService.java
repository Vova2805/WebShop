package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Property;


public interface IPropertyService extends IService<IGenericDAO<Property, Integer>, Property, Integer> {

}
