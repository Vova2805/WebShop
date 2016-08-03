package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import com.webshop.model.classes.extra.ImageDecorator;
import com.webshop.model.enums.ImageSize;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
public class Subcategory extends ImageDecorator {
    public static transient List<ImageSize> supportedSizes;

    static {
        supportedSizes = new ArrayList<ImageSize>() {{
            add(ImageSize.STANDART_128);
        }};
    }

    @Expose
    private int id;
    @Expose
    private int categoryId;
    @Expose
    private String title;
    @Expose
    private Category categoryByCategoryId;
    private transient List<SubcategoryGroup> subcategoryGroupsBySubcategoryId;
    @Expose
    private Map<String, String> images;

    private List<Product> goods;

    public Subcategory() {
    }

    public Subcategory(int categoryId, String title, Category categoryByCategoryId) {
        this.categoryId = categoryId;
        this.title = title;
        this.categoryByCategoryId = categoryByCategoryId;
        subcategoryGroupsBySubcategoryId = new ArrayList<>();
    }

    @Transient
    public Map<String, String> getImages() {
        images = new HashMap<>();
        supportedSizes.forEach(imageSize -> {
            images.put(imageSize.toString(), getImageSrc() + getExtension(imageSize));
        });
        return images;
    }

    @Transient
    @Override
    public String getImageSrc() {
        String base = super.getImageSrc();
        String src = base + categoryImgBaseUrl(categoryId) + subcategoryImgTitle();
        boolean isExists = isImageExists(getRealBaseImgURL() + categoryImgBaseUrl(categoryId) + subcategoryImgTitle(), supportedSizes);
        if (!isExists)
            src = base + defaultSubcategoryImgPath();
        return src;
    }

    @Transient
    public List<Product> getGoods() {
        if (goods == null) {
            goods = initializeProducts();
        }
        return goods;
    }

    private List<Product> initializeProducts() {
        List<Product> goods = new ArrayList<>();
        getSubcategoryGroupsBySubcategoryId().forEach(group -> {
            goods.addAll(group.getGoodsBySubcategoryGroupId());
        });
        return goods;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategoryId", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "categoryId", nullable = false, insertable = false, updatable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    @Basic
    @Column(name = "subcategoryTitle", nullable = false, length = 60)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subcategory that = (Subcategory) o;

        if (id != that.id) return false;
        if (categoryId != that.categoryId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + categoryId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", nullable = false)
    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(Category categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @OneToMany(mappedBy = "subcategoryBySubcategoryId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<SubcategoryGroup> getSubcategoryGroupsBySubcategoryId() {
        return subcategoryGroupsBySubcategoryId;
    }

    public void setSubcategoryGroupsBySubcategoryId(List<SubcategoryGroup> subcategoryGroupsBySubcategoryId) {
        this.subcategoryGroupsBySubcategoryId = subcategoryGroupsBySubcategoryId;
    }

    private String defaultSubcategoryImgPath() {
        return "res/" + "subcategory";
    }

    private String subcategoryImgTitle() {
        return "sub_" + id;
    }

    @Override
    public void initializeImages() {
        images = initializeImages(supportedSizes);
    }

    @Override
    public void saveImagesToFS(byte[] bytes) throws IOException {
        saveImagesToFS(bytes, categoryImgBaseUrl(categoryId), subcategoryImgTitle(), supportedSizes);
    }

    @Override
    public void deleteImagesFromFS() {
        deleteImagesFromFS(categoryImgBaseUrl(categoryId) + subcategoryImgTitle(), supportedSizes);
    }
}
