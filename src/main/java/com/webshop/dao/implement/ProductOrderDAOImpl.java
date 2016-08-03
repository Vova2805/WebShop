package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IProductOrderDAO;
import com.webshop.model.classes.ProductOrderProduct;
import org.springframework.stereotype.Repository;

@Repository
public class ProductOrderDAOImpl extends GenericDAOImpl<ProductOrderProduct, Integer> implements IProductOrderDAO {

}
