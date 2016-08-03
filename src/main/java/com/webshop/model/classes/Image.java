package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import com.webshop.model.classes.extra.ImageDecorator;
import com.webshop.model.enums.ImageSize;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Entity
public class Image extends ImageDecorator {
    public static transient List<ImageSize> supportedSizes;

    static {
        supportedSizes = new ArrayList<ImageSize>() {{
            add(ImageSize.SMALL);
            add(ImageSize.MEDIUM);
            add(ImageSize.LARGE);
        }};
    }

    @Expose
    protected Map<String, String> images;
    @Expose
    private int imageId;
    @Expose
    private int productId;
    @Expose
    private String imageSrc;
    private Product productByProductId;


    public Image() {
    }

    public Image(int productId) {
        this.productId = productId;
    }


    protected static final String defaultGoodImgPath() {
        return "res/product";
    }

    @Transient
    @Override
    public String getImageSrc() {
        String path = super.getImageSrc() + GoodImgUrlById();
        return path;
    }

    @Transient
    public Map<String, String> getImages() {
        if (images == null)
            initializeImages();
        return images;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId", nullable = false)
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

        Image image = (Image) o;

        if (imageId != image.imageId) return false;
        if (productId != image.productId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = imageId;
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

    private String GoodImgUrlById() {
        String path = ImageFolderURLById() + imageId;
        if (!isImageExists(getRealBaseImgURL() + path, supportedSizes)) {
            path = defaultGoodImgPath();
        }
        return path;
    }

    private String ImageFolderURLById() {
        int folder = imageId / MAX_GOODS_IN_FOLDER;
        return "goods/" + folder + "/";
    }

    public void deleteImagesFromFS() {
        deleteImagesFromFS(ImageFolderURLById() + imageId, supportedSizes);
    }

    public void saveImagesToFS(byte[] bytes) throws IOException {
        saveImagesToFS(bytes, ImageFolderURLById(), imageId + "", supportedSizes);
    }

    public void initializeImages() {
        images = initializeImages(supportedSizes);
    }

}
