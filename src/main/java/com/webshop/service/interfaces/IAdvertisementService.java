package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Advertisement;


public interface IAdvertisementService extends IService<IGenericDAO<Advertisement, Integer>, Advertisement, Integer> {

}
