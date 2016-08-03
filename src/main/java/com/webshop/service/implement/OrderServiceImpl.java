package com.webshop.service.implement;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.dao.interfaces.IOrderDAO;
import com.webshop.model.classes.ProductOrder;
import com.webshop.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl extends ServiceImpl<IGenericDAO<ProductOrder, Integer>, ProductOrder, Integer> implements IOrderService {

    @Autowired
    private IOrderDAO orderDAO;

    public OrderServiceImpl() {
    }

    @Autowired
    public OrderServiceImpl(IGenericDAO<ProductOrder, Integer> orderDAO) {
        super(orderDAO);
    }

    @Override
    public List<ProductOrder> getNotReleased() {
        List<ProductOrder> orders = new ArrayList<>();
        orders.addAll(orderDAO.getAll());
        getNotReleased(orders);
        return orders;
    }

    @Override
    public List<ProductOrder> getNotReleased(List<ProductOrder> orders) {
        return orders.stream().filter(productOrder -> !productOrder.isReleased()).collect(Collectors.toList());
    }

    @Override
    public List<ProductOrder> getLatest(int limit) {
        return orderDAO.getLatest(limit);
    }
}
