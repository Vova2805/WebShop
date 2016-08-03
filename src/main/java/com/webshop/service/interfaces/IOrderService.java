package com.webshop.service.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.ProductOrder;

import java.util.List;


public interface IOrderService extends IService<IGenericDAO<ProductOrder, Integer>, ProductOrder, Integer> {

    List<ProductOrder> getNotReleased();

    List<ProductOrder> getNotReleased(List<ProductOrder> orders);

    List<ProductOrder> getLatest(int limit);

}
