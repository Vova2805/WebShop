package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.IPropertyClassDAO;
import com.webshop.model.classes.PropertyClass;
import com.webshop.service.interfaces.IPropertyClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PropertyClassServiceImpl extends ServiceImpl<IGenericDAO<PropertyClass, Integer>, PropertyClass, Integer> implements IPropertyClassService {

    @Autowired
    private IPropertyClassDAO propertyClassDAO;

    public PropertyClassServiceImpl() {
    }

    @Autowired
    public PropertyClassServiceImpl(IGenericDAO<PropertyClass, Integer> propertyClassDAO) {
        super(propertyClassDAO);
    }

    @Override
    public List<PropertyClass> getPropertyClassByTitle(String title) {
        List<PropertyClass> result = new ArrayList<>();
        List<PropertyClass> list = propertyClassDAO.getAll();
        for (PropertyClass propertyClass : list) {
            if (propertyClass.getPropertyClassTitle().equals(title))
                result.add(propertyClass);
        }
        return result;
    }
}
