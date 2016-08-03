package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import com.webshop.model.classes.extra.ImageDecorator;
import com.webshop.model.enums.ImageSize;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
public class Category extends ImageDecorator {
    public static transient List<ImageSize> supportedSizes;

    static {
        supportedSizes = new ArrayList<ImageSize>() {{
            add(ImageSize.STANDART_128);
        }};
    }

    @Expose
    private int id;
    @Expose
    private String title;
    @Expose
    private Map<String, String> images;

    private transient List<Subcategory> subcategoriesByCategoryId;
    private transient List<SubcategoryGroup> subcategoryGroupsBySubcategoryId;
    private List<Product> goods;

    public Category() {
    }

    public Category(String title) {
        this.title = title;
        subcategoriesByCategoryId = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "categoryTitle", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    public String getImageSrc() {
        String src = super.getImageSrc() + getCategoryImageFolder() + categoryImgTitle();
        boolean isExists = isImageExists(getRealImagePath(), supportedSizes);
        if (!isExists)
            src = super.getImageSrc() + defaultCategoryImgPath();
        return src;
    }

    @Transient
    private String getCategoryImageFolder() {
        return categoryImgBaseUrl();
    }

    @Transient
    private String getRealImagePath() {
        return getRealBaseImgURL() + categoryImgBaseUrl() + categoryImgTitle();
    }

    @Transient
    public List<SubcategoryGroup> getSubcategoryGroupsBySubcategoryId() {
        if (subcategoryGroupsBySubcategoryId == null) {
            subcategoryGroupsBySubcategoryId = initializeGroups();
        }
        return subcategoryGroupsBySubcategoryId;
    }

    @Transient
    public List<Product> getGoods() {
        if (goods == null) {
            goods = initializeProducts();
        }
        return goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        if (title != null ? !title.equals(category.title) : category.title != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }


    @OneToMany(mappedBy = "categoryByCategoryId", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Subcategory> getSubcategoriesByCategoryId() {
        return subcategoriesByCategoryId;
    }

    public void setSubcategoriesByCategoryId(List<Subcategory> subcategoriesByCategoryId) {
        this.subcategoriesByCategoryId = subcategoriesByCategoryId;
    }

    private String categoryImgBaseUrl() {
        return super.categoryImgBaseUrl(id);
    }

    private String categoryImgTitle() {
        return "category_" + id;
    }

    private String defaultCategoryImgPath() {
        return "res/" + "category";
    }

    @Override
    public void initializeImages() {
        images = initializeImages(supportedSizes);
    }

    @Override
    public void saveImagesToFS(byte[] bytes) throws IOException {
        saveImagesToFS(bytes, categoryImgBaseUrl(), categoryImgTitle(), supportedSizes);
    }

    @Override
    public void deleteImagesFromFS() {
        deleteImagesFromFS(getCategoryImageFolder() + categoryImgTitle(), supportedSizes);
    }

    public void deleteDir() {
        String dirPath = getRealBaseImgURL() + categoryImgBaseUrl();
        File dir = new File(dirPath);
        dir.delete();
    }

    private List<SubcategoryGroup> initializeGroups() {
        List<SubcategoryGroup> subcategoryGroups = new ArrayList<>();
        this.subcategoriesByCategoryId
                .forEach(subcategory -> {
                    subcategoryGroups.addAll(subcategory.getSubcategoryGroupsBySubcategoryId());
                });
        return subcategoryGroups;
    }

    private List<Product> initializeProducts() {
        List<Product> products = new ArrayList<>();
        this.getSubcategoriesByCategoryId()
                .forEach(subcategory -> {
                    subcategory.getSubcategoryGroupsBySubcategoryId()
                            .forEach(group -> {
                                products.addAll(group.getGoodsBySubcategoryGroupId());
                            });
                });
        return products;
    }
}
