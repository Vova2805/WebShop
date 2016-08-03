package com.webshop.dao.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Cart;

import java.util.List;


public interface ICartDAO extends IGenericDAO<Cart, Integer> {

    /**
     * Get latest added limit elements in cart
     *
     * @param limit
     * @return
     */
    List<Cart> getLatest(int limit);
}
