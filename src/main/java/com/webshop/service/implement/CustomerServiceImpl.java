package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.ICustomerDAO;
import com.webshop.exeption.UserAlreadyExistsException;
import com.webshop.model.classes.Customer;
import com.webshop.model.enums.UserRole;
import com.webshop.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl extends ServiceImpl<IGenericDAO<Customer, Integer>, Customer, Integer> implements ICustomerService {

    @Autowired
    private ICustomerDAO customerDAO;


    public CustomerServiceImpl() {
    }

    @Autowired
    public CustomerServiceImpl(IGenericDAO<Customer, Integer> customerDAO) {
        super(customerDAO);
    }

    @Override
    @Transactional
    public List<Customer> getCustomer(String name) {
        return customerDAO.getCustomerByName(name);
    }

    @Override
    @Transactional
    public boolean toggleEnable(int userId) {
        Customer customer = customerDAO.get(userId);
        customer.setEnabled(!customer.isEnabled());
        customerDAO.update(customer);
        return customer.isEnabled();
    }

    @Override
    @Transactional
    public void registerNewCustomer(Customer customer) throws UserAlreadyExistsException {
        //check if customer already exists

        if (customerDAO.alreadyExists(customer)) {
            throw new UserAlreadyExistsException(customer.getCustomerName());
        }
        //register new customer
        customerDAO.add(customer);
    }

    @Override
    public List<Customer> getUsers(UserRole userRole) {
        List<Customer> customers = ((List<Customer>) (Object) customerDAO.getAll());
        customers = customers.stream()
                .filter(o -> {
                    return o.getRoleByRoleId().getRole().equals(userRole.toString().toUpperCase());
                }).collect(Collectors.toList());
        return customers;
    }

    @Override
    public List<Customer> getUsers(boolean locked) {
        return customerDAO.getAll(locked);
    }

    @Override
    public void delete(Integer id) {
        Customer customer = customerDAO.get(id);
        customer.deleteImagesFromFS();
        super.delete(id);
    }
}
