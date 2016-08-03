package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.ISubscribedUserDAO;
import com.webshop.model.classes.SubscribedUser;
import com.webshop.service.interfaces.ISubscribedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscribedUserServiceImpl extends ServiceImpl<IGenericDAO<SubscribedUser, Integer>, SubscribedUser, Integer> implements ISubscribedUserService {

    @Autowired
    private ISubscribedUserDAO subscribedUserDAO;

    public SubscribedUserServiceImpl() {
    }

    @Autowired
    public SubscribedUserServiceImpl(IGenericDAO<SubscribedUser, Integer> subscribedUserDAO) {
        super(subscribedUserDAO);
    }
}
