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
public class SubcategoryGroup extends ImageDecorator {
    public static transient List<ImageSize> supportedSizes;

    static {
        supportedSizes = new ArrayList<ImageSize>() {{
            add(ImageSize.STANDART_128);
        }};
    }

    @Expose
    private int id;
    @Expose
    private int subcategoryId;
    @Expose
    private String title;
    private transient List<Product> goodsBySubcategoryGroupId;
    private Subcategory subcategoryBySubcategoryId;
    private transient List<SubcategoryGroup> subcategoryGroupsBySubcategoryId;
    @Expose
    private Map<String, String> images;

    public SubcategoryGroup() {
    }

    public SubcategoryGroup(int subcategoryId, String title, Subcategory subcategoryBySubcategoryId) {
        this.subcategoryId = subcategoryId;
        this.title = title;
        this.subcategoryBySubcategoryId = subcategoryBySubcategoryId;
        goodsBySubcategoryGroupId = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategoryGroupId", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "subcategoryId", nullable = false, insertable = false, updatable = false)
    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    @Basic
    @Column(name = "subcategoryGroupTitle", nullable = false, length = 60)
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
    @Override
    public String getImageSrc() {
        String base = super.getImageSrc();
        String src = base + getCategoryFolder() + groupImgTitle();
        boolean isExists = isImageExists(getRealBaseImgURL() + getCategoryFolder() + groupImgTitle(), supportedSizes);
        if (!isExists)
            src = base + defaultGroupImgPath();
        return src;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubcategoryGroup that = (SubcategoryGroup) o;

        if (id != that.id) return false;
        if (subcategoryId != that.subcategoryId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + subcategoryId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }


    @OneToMany(mappedBy = "subcategoryGroupBySubcategoryGroupId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Product> getGoodsBySubcategoryGroupId() {
        return goodsBySubcategoryGroupId;
    }

    public void setGoodsBySubcategoryGroupId(List<Product> goodsBySubcategoryGroupId) {
        this.goodsBySubcategoryGroupId = goodsBySubcategoryGroupId;
    }

    @ManyToOne
    @JoinColumn(name = "subcategoryId", referencedColumnName = "subcategoryId", nullable = false)
    public Subcategory getSubcategoryBySubcategoryId() {
        return subcategoryBySubcategoryId;
    }

    public void setSubcategoryBySubcategoryId(Subcategory subcategoryBySubcategoryId) {
        this.subcategoryBySubcategoryId = subcategoryBySubcategoryId;
    }

    @Transient
    private String getCategoryFolder() {
        return categoryImgBaseUrl(subcategoryBySubcategoryId.getCategoryId());
    }

    private String groupImgTitle() {
        return "group_" + subcategoryId + "_" + id;
    }

    private String defaultGroupImgPath() {
        return "res/" + "group";
    }

    @Override
    public void initializeImages() {
        images = initializeImages(supportedSizes);
    }

    @Override
    public void saveImagesToFS(byte[] bytes) throws IOException {
        saveImagesToFS(bytes, getCategoryFolder(), groupImgTitle(), supportedSizes);
    }

    @Override
    public void deleteImagesFromFS() {
        deleteImagesFromFS(getCategoryFolder() + groupImgTitle(), supportedSizes);
    }
}
