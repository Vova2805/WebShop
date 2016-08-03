package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import com.webshop.model.classes.extra.ImageDecorator;
import com.webshop.model.enums.ImageSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Customer extends ImageDecorator {

    public static transient List<ImageSize> supportedSizes;

    static {
        supportedSizes = new ArrayList<ImageSize>() {{
            add(ImageSize.STANDART_128);
            add(ImageSize.AVATAR_SMALL);
        }};
    }

    @Expose
    private int customerId;
    @Expose
    private Integer addressId;
    @Expose
    private String customerName;
    @Expose
    private String email;

    private transient String password;
    @Expose
    private boolean enabled;
    @Expose
    private String phone;
    @Expose
    private int roleId;
    private List<AdminLog> adminLogsByCustomerId;
    private List<Answer> answersByCustomerId;
    private List<Cart> cartsByCustomerId;
    private List<Comment> commentsByCustomerId;
    private List<Comparison> comparisonsByCustomerId;
    @Expose
    private Address addressByAddressId;
    private Role roleByRoleId;
    private List<ProductOrder> productOrdersByCustomerId;
    private List<Report> reportsByCustomerId;
    private List<ViewedProduct> viewedProductsByCustomerId;
    private List<WatchedPrice> watchedPricesByCustomerId;
    private List<Wish> wishesByCustomerId;

    @Expose
    private Map<String, String> images;

    public Customer() {
    }

    public Customer(String customerName, String password) {
        this.customerName = customerName;
        this.password = password;
        this.enabled = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "addressId", nullable = true, insertable = false, updatable = false)
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "customerName", nullable = false, length = -1)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
    @Column(name = "roleId", nullable = false, insertable = false, updatable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerId != customer.customerId) return false;
        if (enabled != customer.enabled) return false;
        if (roleId != customer.roleId) return false;
        if (addressId != null ? !addressId.equals(customer.addressId) : customer.addressId != null) return false;
        if (customerName != null ? !customerName.equals(customer.customerName) : customer.customerName != null)
            return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + roleId;
        return result;
    }

    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<AdminLog> getAdminLogsByCustomerId() {
        return adminLogsByCustomerId;
    }

    public void setAdminLogsByCustomerId(List<AdminLog> adminLogsByCustomerId) {
        this.adminLogsByCustomerId = adminLogsByCustomerId;
    }

    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Answer> getAnswersByCustomerId() {
        return answersByCustomerId;
    }

    public void setAnswersByCustomerId(List<Answer> answersByCustomerId) {
        this.answersByCustomerId = answersByCustomerId;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Cart> getCartsByCustomerId() {
        return cartsByCustomerId;
    }

    public void setCartsByCustomerId(List<Cart> cartsByCustomerId) {
        this.cartsByCustomerId = cartsByCustomerId;
    }

    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Comment> getCommentsByCustomerId() {
        return commentsByCustomerId;
    }

    public void setCommentsByCustomerId(List<Comment> commentsByCustomerId) {
        this.commentsByCustomerId = commentsByCustomerId;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Comparison> getComparisonsByCustomerId() {
        return comparisonsByCustomerId;
    }

    public void setComparisonsByCustomerId(List<Comparison> comparisonsByCustomerId) {
        this.comparisonsByCustomerId = comparisonsByCustomerId;
    }

    @ManyToOne
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId", nullable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<ProductOrder> getProductOrdersByCustomerId() {
        return productOrdersByCustomerId;
    }

    public void setProductOrdersByCustomerId(List<ProductOrder> productOrdersByCustomerId) {
        this.productOrdersByCustomerId = productOrdersByCustomerId;
    }

    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Report> getReportsByCustomerId() {
        return reportsByCustomerId;
    }

    public void setReportsByCustomerId(List<Report> reportsByCustomerId) {
        this.reportsByCustomerId = reportsByCustomerId;
    }

    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<ViewedProduct> getViewedProductsByCustomerId() {
        return viewedProductsByCustomerId;
    }

    public void setViewedProductsByCustomerId(List<ViewedProduct> viewedProductsByCustomerId) {
        this.viewedProductsByCustomerId = viewedProductsByCustomerId;
    }

    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<WatchedPrice> getWatchedPricesByCustomerId() {
        return watchedPricesByCustomerId;
    }

    public void setWatchedPricesByCustomerId(List<WatchedPrice> watchedPricesByCustomerId) {
        this.watchedPricesByCustomerId = watchedPricesByCustomerId;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "customerByCustomerId", cascade = CascadeType.REFRESH)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Wish> getWishesByCustomerId() {
        return wishesByCustomerId;
    }

    public void setWishesByCustomerId(List<Wish> wishesByCustomerId) {
        this.wishesByCustomerId = wishesByCustomerId;
    }

    private String CustomerImageURL() {
        return "avatars/";
    }

    @Transient
    private String getDefault() {
        return "res/customer";
    }

    @Transient
    public String getImageSrc() {
        return super.getImageSrc() + CustomerImgURLChecked();
    }

    @Override
    public void initializeImages() {
        images = initializeImages(supportedSizes);
    }

    @Override
    public void saveImagesToFS(byte[] bytes) throws IOException {
        saveImagesToFS(bytes, CustomerImageURL(), customerId + "", supportedSizes);
    }

    @Override
    public void deleteImagesFromFS() {
        deleteImagesFromFS(CustomerImageURL() + customerId, supportedSizes);
    }

    @Transient
    public Map<String, String> getImages() {
        if (images == null) initializeImages();
        return images;
    }

    private String CustomerImgURLChecked() {
        String path = CustomerImageURL() + customerId;
        if (!isImageExists(getRealBaseImgURL() + path, supportedSizes)) {
            path = getDefault();
        }
        return path;
    }
}
