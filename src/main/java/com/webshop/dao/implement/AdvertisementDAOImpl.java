package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IAdvertisementDAO;
import com.webshop.model.classes.Advertisement;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisementDAOImpl extends GenericDAOImpl<Advertisement, Integer> implements IAdvertisementDAO {


}
