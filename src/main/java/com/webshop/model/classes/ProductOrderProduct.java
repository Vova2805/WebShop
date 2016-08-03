package com.webshop.model.classes;

import com.google.gson.annotations.Expose;

import javax.persistence.*;


@Entity
public class ProductOrderProduct {
    @Expose
    private int productOrderProductId;
    @Expose
    private int productOrderId;
    @Expose
    private int productId;
    @Expose
    private int quantity;
    private Product productByProductId;
    private ProductOrder productOrderByProductOrderId;

    public ProductOrderProduct() {
    }

    public ProductOrderProduct(ProductOrder productOrderByProductOrderId, Product productByProductId, int quantity) {
        this.productOrderByProductOrderId = productOrderByProductOrderId;
        this.productByProductId = productByProductId;
        this.quantity = quantity;

        productOrderId = productOrderByProductOrderId.getProductOrderId();
        productId = productByProductId.getProductId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productOrderProductId", nullable = false)
    public int getProductOrderProductId() {
        return productOrderProductId;
    }

    public void setProductOrderProductId(int productOrderProductId) {
        this.productOrderProductId = productOrderProductId;
    }

    @Basic
    @Column(name = "productOrderId", nullable = false, insertable = false, updatable = false)
    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
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
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOrderProduct that = (ProductOrderProduct) o;

        if (productOrderId != that.productOrderId) return false;
        if (productId != that.productId) return false;
        if (quantity != that.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productOrderId;
        result = 31 * result + productId;
        result = 31 * result + quantity;
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
    @JoinColumn(name = "productOrderId", referencedColumnName = "productOrderId", nullable = false)
    public ProductOrder getProductOrderByProductOrderId() {
        return productOrderByProductOrderId;
    }

    public void setProductOrderByProductOrderId(ProductOrder productOrderByProductOrderId) {
        this.productOrderByProductOrderId = productOrderByProductOrderId;
    }
}
