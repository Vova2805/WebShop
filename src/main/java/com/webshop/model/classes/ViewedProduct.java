package com.webshop.model.classes;

import com.google.gson.annotations.Expose;

import javax.persistence.*;


@Entity
public class ViewedProduct {
    @Expose
    private int viewedId;
    @Expose
    private int customerId;
    @Expose
    private int productId;
    private Product productByProductId;
    private Customer customerByCustomerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viewedId", nullable = false)
    public int getViewedId() {
        return viewedId;
    }

    public void setViewedId(int viewedId) {
        this.viewedId = viewedId;
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
    @Column(name = "productId", nullable = false, insertable = false, updatable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewedProduct that = (ViewedProduct) o;

        if (viewedId != that.viewedId) return false;
        if (customerId != that.customerId) return false;
        if (productId != that.productId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = viewedId;
        result = 31 * result + customerId;
        result = 31 * result + productId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }
}
