package com.webshop.dao.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Customer;

import java.util.List;


public interface ICustomerDAO extends IGenericDAO<Customer, Integer> {

    List<Customer> getCustomerByName(String name);

    boolean alreadyExists(Customer customer);

    List<Customer> getAll(boolean locked);
}
