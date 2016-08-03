package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IPropertyDAO;
import com.webshop.model.classes.Property;
import org.springframework.stereotype.Repository;

@Repository
public class PropertyDAOImpl extends GenericDAOImpl<Property, Integer> implements IPropertyDAO {

}
