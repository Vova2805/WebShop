package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.ICustomerDAO;
import com.webshop.dao.interfaces.IRoleDAO;
import com.webshop.model.classes.Customer;
import com.webshop.model.classes.Role;
import com.webshop.model.enums.UserRole;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl extends GenericDAOImpl<Customer, Integer> implements ICustomerDAO {

    @Autowired
    private IRoleDAO roleDAO;

    @Override
    public boolean alreadyExists(Customer customer) {
        List<Customer> customers = getAll();
        for (Customer existedCustomer : customers) {
            if (customer.getCustomerName().equals(existedCustomer.getCustomerName())) {
                return true;
            }
        }
        return false;
    }

    public List<Customer> getAll(boolean locked) {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("enabled", !locked));
        return criteria.list();
    }

    @Override
    public Customer add(Customer customer) {
        Role role = roleDAO.addRole(UserRole.USER);
        customer.setRoleByRoleId(role);
        return super.add(customer);
    }

    @Override
    public List<Customer> getCustomerByName(String name) {
        String queryString = "from Customer where customerName = :name";
        Query query = super.getSession().createQuery(queryString).setCacheable(true);
        query.setString("name", name);
        return (List<Customer>) query.list();
    }

}
