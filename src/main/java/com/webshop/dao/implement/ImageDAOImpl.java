package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IImageDAO;
import com.webshop.model.classes.Image;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDAOImpl extends GenericDAOImpl<Image, Integer> implements IImageDAO {

}
