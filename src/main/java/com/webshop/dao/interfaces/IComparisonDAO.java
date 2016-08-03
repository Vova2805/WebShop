package com.webshop.dao.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Comparison;

import java.util.List;


public interface IComparisonDAO extends IGenericDAO<Comparison, Integer> {

    List<Comparison> getLatest(int limit);
}
