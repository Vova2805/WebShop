package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.ISubscribedUserDAO;
import com.webshop.model.classes.SubscribedUser;
import org.springframework.stereotype.Repository;

@Repository
public class SubscribedUserDAOImpl extends GenericDAOImpl<SubscribedUser, Integer> implements ISubscribedUserDAO {

}
