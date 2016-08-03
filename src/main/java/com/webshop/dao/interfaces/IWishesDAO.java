package com.webshop.dao.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Wish;

import java.util.List;


public interface IWishesDAO extends IGenericDAO<Wish, Integer> {

    List<Wish> getLatest(int limit);
}
