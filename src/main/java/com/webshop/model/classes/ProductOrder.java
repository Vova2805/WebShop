package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import com.webshop.model.enums.OrderStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ProductOrder {
    @Expose
    private int productOrderId;
    @Expose
    private int customerId;
    @Expose
    private int deliveryAddress;
    @Expose
    private Timestamp productOrderDate;
    @Expose
    private String productOrderStatus;
    @Expose
    private boolean paid;
    @Expose
    private double total;
    private Address addressByDeliveryAddress;
    private Customer customerByCustomerId;
    @Expose
    private boolean isReleased;
    private List<ProductOrderProduct> productOrderProductsByProductOrderId;

    public ProductOrder() {
    }

    public ProductOrder(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
        customerId = customerByCustomerId.getCustomerId();
        deliveryAddress = customerByCustomerId.getAddressId();
        productOrderDate = new Timestamp((new java.util.Date()).getTime());
        productOrderStatus = OrderStatus.SHIPPED.toString();
        paid = false;
        total = 0;
        addressByDeliveryAddress = customerByCustomerId.getAddressByAddressId();
        isReleased = false;
        productOrderProductsByProductOrderId = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productOrderId", nullable = false)
    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
    }

    @Basic
    @Column(name = "customerId", nullable = false, insertable = false, updatable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "deliveryAddress", nullable = true, insertable = false, updatable = false)
    public int getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(int deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Basic
    @Column(name = "productOrderDate", nullable = false)
    public Timestamp getProductOrderDate() {
        return productOrderDate;
    }

    public void setProductOrderDate(Timestamp productOrderDate) {
        this.productOrderDate = productOrderDate;
    }

    @Basic
    @Column(name = "isReleased", nullable = false)
    public boolean isReleased() {
        return isReleased;
    }

    public void setReleased(boolean released) {
        isReleased = released;
    }

    @Basic
    @Column(name = "productOrderStatus", nullable = false, length = -1)
    public String getProductOrderStatus() {
        return productOrderStatus;
    }

    public void setProductOrderStatus(String productOrderStatus) {
        this.productOrderStatus = productOrderStatus;
    }

    @Basic
    @Column(name = "paid", nullable = false)
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOrder that = (ProductOrder) o;

        if (productOrderId != that.productOrderId) return false;
        if (customerId != that.customerId) return false;
        if (deliveryAddress != that.deliveryAddress) return false;
        if (paid != that.paid) return false;
        if (Double.compare(that.total, total) != 0) return false;
        if (productOrderDate != null ? !productOrderDate.equals(that.productOrderDate) : that.productOrderDate != null)
            return false;
        if (productOrderStatus != null ? !productOrderStatus.equals(that.productOrderStatus) : that.productOrderStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        int temp;
        result = productOrderId;
        result = 31 * result + customerId;
        result = 31 * result + deliveryAddress;
        result = 31 * result + (productOrderDate != null ? productOrderDate.hashCode() : 0);
        result = 31 * result + (productOrderStatus != null ? productOrderStatus.hashCode() : 0);
        result = 31 * result + (paid ? 1 : 0);
        temp = (int) (total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "deliveryAddress", referencedColumnName = "addressId", nullable = false)
    public Address getAddressByDeliveryAddress() {
        return addressByDeliveryAddress;
    }

    public void setAddressByDeliveryAddress(Address addressByDeliveryAddress) {
        this.addressByDeliveryAddress = addressByDeliveryAddress;
        this.deliveryAddress = addressByDeliveryAddress.getAddressId();
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @OneToMany(mappedBy = "productOrderByProductOrderId")
    public List<ProductOrderProduct> getProductOrderProductsByProductOrderId() {
        return productOrderProductsByProductOrderId;
    }

    public void setProductOrderProductsByProductOrderId(List<ProductOrderProduct> productOrderProductsByProductOrderId) {
        this.productOrderProductsByProductOrderId = productOrderProductsByProductOrderId;
    }

}
