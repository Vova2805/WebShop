package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.exeption.UserAlreadyExistsException;
import com.webshop.model.classes.Customer;
import com.webshop.model.enums.UserRole;

import java.util.List;


public interface ICustomerService extends IService<IGenericDAO<Customer, Integer>, Customer, Integer> {

    /**
     * @param name
     * @return
     */
    List<Customer> getCustomer(String name);

    /**
     * @param userId
     * @return
     */
    boolean toggleEnable(int userId);

    /**
     * @param customer
     * @return
     * @throws UserAlreadyExistsException
     */
    void registerNewCustomer(Customer customer) throws UserAlreadyExistsException;

    List<Customer> getUsers(UserRole userRole);

    List<Customer> getUsers(boolean locked);

}
