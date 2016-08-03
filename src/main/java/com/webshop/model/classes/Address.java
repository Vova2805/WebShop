package com.webshop.model.classes;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {
    @Expose
    private int addressId;
    @Expose
    private String country;
    @Expose
    private String region;
    @Expose
    private String city;
    @Expose
    private String street;
    @Expose
    private Integer buildingNumber;
    private List<Customer> customersByAddressId;
    private List<ProductOrder> productOrdersByAddressId;
    private List<Shop> shopsByAddressId;

    public Address() {
        this("", "", "", "");
    }

    public Address(String country, String region, String city, String street) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        customersByAddressId = new ArrayList<>();
        productOrdersByAddressId = new ArrayList<>();
        shopsByAddressId = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressId", nullable = false)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "city", nullable = false, length = -1)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = false, length = -1)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "country", nullable = false, length = -1)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "region", nullable = false, length = -1)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "buildingNumber", nullable = true)
    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Address address = (Address) other;
        return Objects.equal(this.country, address.country)
                && Objects.equal(this.street, address.street)
                && Objects.equal(this.city, address.city)
                && Objects.equal(this.region, address.region)
                && Objects.equal(this.country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.addressId, this.street, this.city, this.country, this.country, this.region, this.buildingNumber);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("city", city)
                .add("street", street)
                .add("country", country)
                .add("region", region)
                .add("buildingNumber", buildingNumber)
                .toString();
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public List<Customer> getCustomersByAddressId() {
        return customersByAddressId;
    }

    public void setCustomersByAddressId(List<Customer> customersByAddressId) {
        this.customersByAddressId = customersByAddressId;
    }

    @OneToMany(mappedBy = "addressByDeliveryAddress")
    public List<ProductOrder> getProductOrdersByAddressId() {
        return productOrdersByAddressId;
    }

    public void setProductOrdersByAddressId(List<ProductOrder> productOrdersByAddressId) {
        this.productOrdersByAddressId = productOrdersByAddressId;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public List<Shop> getShopsByAddressId() {
        return shopsByAddressId;
    }

    public void setShopsByAddressId(List<Shop> shopsByAddressId) {
        this.shopsByAddressId = shopsByAddressId;
    }

}
