package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IOrderDAO;
import com.webshop.model.classes.Customer;
import com.webshop.model.classes.ProductOrder;
import com.webshop.model.enums.OrderStatus;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderDAOImpl extends GenericDAOImpl<ProductOrder, Integer> implements IOrderDAO {

    @Override
    public void releaseOrder(ProductOrder productOrder) {
        productOrder.setReleased(true);
    }

    @Override
    public List<ProductOrder> getAllOrders(Customer customer) {
        return customer.getProductOrdersByCustomerId();
    }

    @Override
    public List<ProductOrder> getAllOrders(Date from, Date to) {
        return getAllOrders(from, to, ((List<ProductOrder>) (Object) getAll()));
    }

    @Override
    public List<ProductOrder> getAllOrders(Date from, Date to, List<ProductOrder> orders) {
        return orders.stream().filter(order ->
                order.getProductOrderDate().before(to)
                        && order.getProductOrderDate().after(from))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductOrder> getAllOrders(OrderStatus status) {
        return getAllOrders(status, ((List<ProductOrder>) (Object) getAll()));

    }

    @Override
    public List<ProductOrder> getAllOrders(OrderStatus status, List<ProductOrder> orders) {
        return orders.stream().filter(order ->
                order.getProductOrderStatus().equals(status.toString()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductOrder> getAllOrders(Date from, Date to, Customer customer) {
        return getAllOrders(from, to, customer.getProductOrdersByCustomerId());
    }

    @Override
    public List<ProductOrder> getAllOrders(Customer customer, OrderStatus status) {
        return getAllOrders(status, customer.getProductOrdersByCustomerId());
    }

    @Override
    public List<ProductOrder> getLatest(int limit) {
        String sql = "from ProductOrder ORDER BY productOrderId DESC";
        return getSession()
                .createQuery(sql)
                .setCacheable(true)
                .setMaxResults(limit)
                .list();
    }
}
