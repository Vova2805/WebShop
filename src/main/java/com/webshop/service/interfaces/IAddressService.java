package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Address;


public interface IAddressService extends IService<IGenericDAO<Address, Integer>, Address, Integer> {

}
