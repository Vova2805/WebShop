package com.webshop.model.classes;

import com.google.gson.annotations.Expose;

import javax.persistence.*;


@Entity
public class Cart {
    @Expose
    private int cartProductId;
    @Expose
    private int productId;
    @Expose
    private int customerId;
    @Expose
    private int productAmount;
    private Customer customerByCustomerId;
    @Expose
    private Product productByProductId;

    public Cart() {
    }

    public Cart(Product productByProductId, int productAmount, Customer customerByCustomerId) {
        this.productByProductId = productByProductId;
        this.customerByCustomerId = customerByCustomerId;
        this.productAmount = productAmount;
        this.customerId = customerByCustomerId.getCustomerId();
        this.productId = productByProductId.getProductId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartProductId", nullable = false)
    public int getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(int cartProductId) {
        this.cartProductId = cartProductId;
    }

    @Basic
    @Column(name = "productId", nullable = false, insertable = false, updatable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
    @Column(name = "productAmount", nullable = false)
    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (cartProductId != cart.cartProductId) return false;
        if (productId != cart.productId) return false;
        if (customerId != cart.customerId) return false;
        if (productAmount != cart.productAmount) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartProductId;
        result = 31 * result + productId;
        result = 31 * result + customerId;
        result = 31 * result + productAmount;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @ManyToOne
    @JoinColumn(name = "ProductId", referencedColumnName = "productId", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }
}
