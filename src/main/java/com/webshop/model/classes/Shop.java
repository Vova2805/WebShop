package com.webshop.model.classes;

import com.google.gson.annotations.Expose;

import javax.persistence.*;


@Entity
public class Shop {
    @Expose
    private int shopId;
    @Expose
    private int addressId;
    @Expose
    private String phone;
    @Expose
    private String email;
    @Expose
    private String description;
    @Expose
    private String position;
    @Expose
    private Address addressByAddressId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopId", nullable = false)
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "addressId", nullable = false, insertable = false, updatable = false)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "position", nullable = true, length = -1)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @ManyToOne
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (shopId != shop.shopId) return false;
        if (phone != null ? !phone.equals(shop.phone) : shop.phone != null) return false;
        if (email != null ? !email.equals(shop.email) : shop.email != null) return false;
        if (description != null ? !description.equals(shop.description) : shop.description != null) return false;
        if (position != null ? !position.equals(shop.position) : shop.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shopId;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
