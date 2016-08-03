package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import com.webshop.model.classes.extra.ImageDecorator;
import com.webshop.model.enums.Availability;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class Product extends ImageDecorator {
    @Expose
    private int productId;
    @Expose
    private int subcategoryGroupId;
    @Expose
    private int categoryId;
    @Expose
    private int subcategoryId;
    @Expose
    private String title;
    @Expose
    private String generalDescr;
    @Expose
    private int availableAmount;
    @Expose
    private boolean isEnabled;
    @Expose
    private int warrantyMonths;
    @Expose
    private double price;
    @Expose
    private Timestamp addedDate;
    @Expose
    private boolean showDiscount;
    @Expose
    private double discount;
    @Expose
    private double rating;
    @Expose
    private String videoYouTube;
    private transient List<Cart> cartsByProductId;
    private transient List<Comment> commentsByProductId;
    private transient List<Comparison> comparisonsByProductId;
    private transient List<DescriptionPart> descriptionPartsByProductId;
    private transient List<Image> imagesByProductId;
    private transient List<ProductOrderProduct> productOrderProductsByProductId;
    private transient SubcategoryGroup subcategoryGroupBySubcategoryGroupId;
    private transient List<ViewedProduct> viewedProductsByProductId;
    private transient List<WatchedPrice> watchedPricesByProductId;
    private transient List<Wish> wishesByProductId;
    private transient List<Property> propertyProductsByProductId;
    @Expose
    private Image firstImage;
    @Expose
    private String availability;
    @Expose
    private String smallImage;


    public Product() {
    }

    public Product(int subcategoryGroupId, String title, SubcategoryGroup subcategoryGroupBySubcategoryGroupId) {
        this.subcategoryGroupId = subcategoryGroupId;
        this.title = title;
        this.subcategoryGroupBySubcategoryGroupId = subcategoryGroupBySubcategoryGroupId;
        this.addedDate = new Timestamp((new Date()).getTime());
        this.isEnabled = true;
        this.generalDescr = "";
        this.availableAmount = 0;
        this.warrantyMonths = 0;
        this.price = 0;
        this.showDiscount = false;
        this.discount = 0;
        this.rating = 0;
        this.cartsByProductId = new ArrayList<>();
        this.commentsByProductId = new ArrayList<>();
        this.comparisonsByProductId = new ArrayList<>();
        this.descriptionPartsByProductId = new ArrayList<>();
        this.imagesByProductId = new ArrayList<>();
        this.productOrderProductsByProductId = new ArrayList<>();
        this.viewedProductsByProductId = new ArrayList<>();
        this.watchedPricesByProductId = new ArrayList<>();
        this.wishesByProductId = new ArrayList<>();
        this.propertyProductsByProductId = new ArrayList<>();
    }

    @Transient
    public Image getFirstImage() {
        if (imagesByProductId.size() > 0 && firstImage == null) {
            firstImage = imagesByProductId.get(0);
        } else if (firstImage == null) {
            firstImage = new Image();
        }
        firstImage.initializeImages();
        return firstImage;
    }

    @Transient
    public String getAvailability() {

        if (availability == null) availability = Availability.IN_STOCK.toString();
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Transient
    @Override
    public String getImageSrc() {
        String productImage = super.getImageSrc() + Image.defaultGoodImgPath();
        if (imagesByProductId.size() > 0) {
            productImage = imagesByProductId.get(0).getImageSrc();
        }
        return productImage;
    }

    @Id
    @GenericGenerator(name = "native", strategy = "increment")
    @GeneratedValue(generator = "native")
    @Column(name = "productId", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "subcategoryGroupId", nullable = false, insertable = false, updatable = false)
    public int getSubcategoryGroupId() {
        return subcategoryGroupId;
    }

    public void setSubcategoryGroupId(int subcategoryGroupId) {
        this.subcategoryGroupId = subcategoryGroupId;
    }

    @Basic
    @Column(name = "productTitle", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "generalDescr", nullable = true)
    public String getGeneralDescr() {
        return generalDescr;
    }

    public void setGeneralDescr(String generalDescr) {
        this.generalDescr = generalDescr;
    }

    @Basic
    @Column(name = "warrantyMonths", nullable = false)
    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    @Basic
    @Column(name = "addedDate", nullable = false)
    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    @Basic
    @Column(name = "videoYouTubeV", nullable = true, length = 50)
    public String getVideoYouTube() {
        return videoYouTube;
    }

    public void setVideoYouTube(String videoYouTube) {
        this.videoYouTube = videoYouTube;
    }

    @Basic
    @Column(name = "availableAmount", nullable = false)
    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    @Basic
    @Column(name = "isEnabled", nullable = false)
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "showDiscount", nullable = true)
    public boolean isShowDiscount() {
        return showDiscount;
    }

    public void setShowDiscount(boolean showDiscount) {
        this.showDiscount = showDiscount;
    }

    @Basic
    @Column(name = "discount", nullable = true)
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "rating", nullable = false)
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (subcategoryGroupId != product.subcategoryGroupId) return false;
        if (warrantyMonths != product.warrantyMonths) return false;
        if (title != null ? !title.equals(product.title) : product.title != null)
            return false;
        if (generalDescr != null ? !generalDescr.equals(product.generalDescr) : product.generalDescr != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + subcategoryGroupId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (generalDescr != null ? generalDescr.hashCode() : 0);
        result = 31 * result + warrantyMonths;
        return result;
    }

    @OneToMany(mappedBy = "productByProductId")
    public List<Cart> getCartsByProductId() {
        return cartsByProductId;
    }

    public void setCartsByProductId(List<Cart> cartsByProductId) {
        this.cartsByProductId = cartsByProductId;
    }

    @OneToMany(mappedBy = "productByProductId")
    public List<Comment> getCommentsByProductId() {
        return commentsByProductId;
    }

    public void setCommentsByProductId(List<Comment> commentsByProductId) {
        this.commentsByProductId = commentsByProductId;
    }

    @OneToMany(mappedBy = "productByProductId")
    public List<Comparison> getComparisonsByProductId() {
        return comparisonsByProductId;
    }

    public void setComparisonsByProductId(List<Comparison> comparisonsByProductId) {
        this.comparisonsByProductId = comparisonsByProductId;
    }


    @OneToMany(mappedBy = "productByProductId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<DescriptionPart> getDescriptionPartsByProductId() {
        return descriptionPartsByProductId;
    }

    public void setDescriptionPartsByProductId(List<DescriptionPart> descriptionPartsByProductId) {
        this.descriptionPartsByProductId = descriptionPartsByProductId;
    }

    public void addDescriptionPart(DescriptionPart descriptionPart) {
        this.descriptionPartsByProductId.add(descriptionPart);
    }

    public void removeDescriptionPart(int descriptionId) {
        this.descriptionPartsByProductId.removeIf(
                dp -> dp.getDescriptionPartId() == descriptionId);
    }

    public void removeDescriptionPart(DescriptionPart descriptionPart) {
        this.descriptionPartsByProductId.remove(descriptionPart);
    }

    public DescriptionPart getDescription(int id) {
        return this.descriptionPartsByProductId.stream()
                .filter(
                        descriptionPart -> descriptionPart.getDescriptionPartId() == id
                )
                .findFirst()
                .get();
    }

    @Transient
    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    @Transient
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Transient
    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    @OneToMany(mappedBy = "productByProductId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Image> getImagesByProductId() {
        return imagesByProductId;
    }

    public void setImagesByProductId(List<Image> imagesByProductId) {
        this.imagesByProductId = imagesByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "subcategoryGroupId", referencedColumnName = "subcategoryGroupId", nullable = false)
    public SubcategoryGroup getSubcategoryGroupBySubcategoryGroupId() {
        return subcategoryGroupBySubcategoryGroupId;
    }

    public void setSubcategoryGroupBySubcategoryGroupId(SubcategoryGroup subcategoryGroupBySubcategoryGroupId) {
        this.subcategoryGroupBySubcategoryGroupId = subcategoryGroupBySubcategoryGroupId;
    }

    @OneToMany(mappedBy = "productByProductId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<ViewedProduct> getViewedProductsByProductId() {
        return viewedProductsByProductId;
    }

    public void setViewedProductsByProductId(List<ViewedProduct> viewedProductsByProductId) {
        this.viewedProductsByProductId = viewedProductsByProductId;
    }

    @OneToMany(mappedBy = "productByProductId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<WatchedPrice> getWatchedPricesByProductId() {
        return watchedPricesByProductId;
    }

    public void setWatchedPricesByProductId(List<WatchedPrice> watchedPricesByProductId) {
        this.watchedPricesByProductId = watchedPricesByProductId;
    }

    @OneToMany(mappedBy = "productByProductId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Wish> getWishesByProductId() {
        return wishesByProductId;
    }

    public void setWishesByProductId(List<Wish> wishesByProductId) {
        this.wishesByProductId = wishesByProductId;
    }

    @OneToMany(mappedBy = "productByProductId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<ProductOrderProduct> getProductOrderProductsByProductId() {
        return productOrderProductsByProductId;
    }

    public void setProductOrderProductsByProductId(List<ProductOrderProduct> productOrderProductsByProductId) {
        this.productOrderProductsByProductId = productOrderProductsByProductId;
    }

    @ManyToMany(mappedBy = "productsByPropertyId", targetEntity = Property.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    public List<Property> getPropertyProductsByProductId() {
        return propertyProductsByProductId;
    }

    public void setPropertyProductsByProductId(List<Property> propertyProductsByProductId) {
        this.propertyProductsByProductId = propertyProductsByProductId;
    }

    @Override
    public void initializeImages() {
        if (firstImage == null) {
            getFirstImage();
        }
        firstImage.initializeImages();
        smallImage = firstImage.images.get("small");
    }

    @Override
    public void saveImagesToFS(byte[] bytes) throws IOException {
        firstImage.saveImagesToFS(bytes);
    }

    @Override
    public void deleteImagesFromFS() {
        imagesByProductId.forEach(image -> {
            image.deleteImagesFromFS();
        });
    }
}
