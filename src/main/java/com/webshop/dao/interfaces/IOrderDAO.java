package com.webshop.dao.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Customer;
import com.webshop.model.classes.ProductOrder;
import com.webshop.model.enums.OrderStatus;

import java.sql.Date;
import java.util.List;


public interface IOrderDAO extends IGenericDAO<ProductOrder, Integer> {

    /**
     * Make order released
     *
     * @param productOrder
     */
    void releaseOrder(ProductOrder productOrder);

    /**
     * Get all orders by date
     *
     * @param from
     * @param to
     * @param orders
     * @return
     */
    List<ProductOrder> getAllOrders(Date from, Date to, List<ProductOrder> orders);


    /**
     * Filtering orders by order status
     *
     * @param status
     * @param orders
     * @return
     */
    List<ProductOrder> getAllOrders(OrderStatus status, List<ProductOrder> orders);

    /**
     * Get all orders by customer
     *
     * @param customer
     * @return
     */
    List<ProductOrder> getAllOrders(Customer customer);

    List<ProductOrder> getAllOrders(Date from, Date to);

    List<ProductOrder> getAllOrders(OrderStatus status);

    List<ProductOrder> getAllOrders(Date from, Date to, Customer customer);

    List<ProductOrder> getAllOrders(Customer customer, OrderStatus status);

    /**
     * Get limit latest orders
     *
     * @param limit
     * @return
     */
    List<ProductOrder> getLatest(int limit);
}
