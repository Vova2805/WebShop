package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.IAddressDAO;
import com.webshop.model.classes.Address;
import com.webshop.service.interfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl extends ServiceImpl<IGenericDAO<Address, Integer>, Address, Integer> implements IAddressService {

    @Autowired
    private IAddressDAO addressDAO;

    public AddressServiceImpl() {
    }

    @Autowired
    public AddressServiceImpl(IGenericDAO<Address, Integer> addressDAO) {
        super(addressDAO);
    }

}
