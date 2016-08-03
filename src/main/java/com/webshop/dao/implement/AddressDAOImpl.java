package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IAddressDAO;
import com.webshop.model.classes.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDAOImpl extends GenericDAOImpl<Address, Integer> implements IAddressDAO {

}
