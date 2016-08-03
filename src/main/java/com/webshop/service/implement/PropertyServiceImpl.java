package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.IPropertyDAO;
import com.webshop.model.classes.Property;
import com.webshop.service.interfaces.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PropertyServiceImpl extends ServiceImpl<IGenericDAO<Property, Integer>, Property, Integer> implements IPropertyService {

    @Autowired
    private IPropertyDAO propertyDAO;

    public PropertyServiceImpl() {
    }

    @Autowired
    public PropertyServiceImpl(IGenericDAO<Property, Integer> propertyDAO) {
        super(propertyDAO);
    }
}
