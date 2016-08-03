package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.SubcategoryGroup;


public interface IGroupService extends IService<IGenericDAO<SubcategoryGroup, Integer>, SubcategoryGroup, Integer> {

}
