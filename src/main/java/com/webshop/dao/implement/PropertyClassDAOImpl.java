package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IPropertyClassDAO;
import com.webshop.model.classes.PropertyClass;
import org.springframework.stereotype.Repository;

@Repository
public class PropertyClassDAOImpl extends GenericDAOImpl<PropertyClass, Integer> implements IPropertyClassDAO {

}
