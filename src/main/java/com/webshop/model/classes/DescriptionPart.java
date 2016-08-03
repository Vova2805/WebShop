package com.webshop.model.classes;

import com.google.gson.annotations.Expose;
import com.webshop.model.classes.extra.ImageDecorator;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;


@Entity
public class DescriptionPart extends ImageDecorator {
    @Expose
    private int descriptionPartId;
    @Expose
    private int productId;
    @Expose
    private String descriptionPartHeader;
    @Expose
    private String descriptionPartBody;
    @Expose
    private boolean hasImg;
    @Expose
    private String imageSrc;

    private Product productByProductId;

    public DescriptionPart() {
    }

    public DescriptionPart(String descriptionPartHeader, String descriptionPartBody, Product productByProductId) {
        this.descriptionPartHeader = descriptionPartHeader;
        this.descriptionPartBody = descriptionPartBody;
        this.productByProductId = productByProductId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "descriptionPartId", nullable = false)
    public int getDescriptionPartId() {
        return descriptionPartId;
    }

    public void setDescriptionPartId(int descriptionPartId) {
        this.descriptionPartId = descriptionPartId;
    }

    @Basic
    @Column(name = "product_productId", nullable = false, insertable = false, updatable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "descriptionPartHeader", nullable = false)
    public String getDescriptionPartHeader() {
        return descriptionPartHeader;
    }

    public void setDescriptionPartHeader(String descriptionPartHeader) {
        this.descriptionPartHeader = descriptionPartHeader;
    }

    @Basic
    @Column(name = "descriptionPartBody", nullable = false)
    public String getDescriptionPartBody() {
        return descriptionPartBody;
    }

    public void setDescriptionPartBody(String descriptionPartBody) {
        this.descriptionPartBody = descriptionPartBody;
    }

    @Basic
    @Column(name = "hasImg", nullable = false)
    public boolean isHasImg() {
        return hasImg;
    }

    public void setHasImg(boolean hasImg) {
        this.hasImg = hasImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DescriptionPart that = (DescriptionPart) o;

        if (descriptionPartId != that.descriptionPartId) return false;
        if (productId != that.productId) return false;
        if (descriptionPartHeader != null ? !descriptionPartHeader.equals(that.descriptionPartHeader) : that.descriptionPartHeader != null)
            return false;
        if (descriptionPartBody != null ? !descriptionPartBody.equals(that.descriptionPartBody) : that.descriptionPartBody != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = descriptionPartId;
        result = 31 * result + productId;
        result = 31 * result + (descriptionPartHeader != null ? descriptionPartHeader.hashCode() : 0);
        result = 31 * result + (descriptionPartBody != null ? descriptionPartBody.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "product_productId", referencedColumnName = "productId", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @Transient
    @Override
    public String getImageSrc() {
        initializeImages();
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    private String descriptionImgUrlById() {
        int folder = getFolderTitle();
        String path = "descr/" + folder + "/" + productId + "_" + descriptionPartId + getExtension();
        return path;
    }

    @Transient
    private String getImageTitle(int productId) {
        return productId + "_" + descriptionPartId + "." + IMAGE_EXTENSION;
    }

    @Transient
    private String getFolderPath() {
        return UPLOAD_LOCATION + "descr/" + getFolderTitle() + "/";
    }

    @Transient
    private int getFolderTitle() {
        return (descriptionPartId / MAX_DESCR_IN_FOLDER);
    }

    @Override
    public void initializeImages() {
        imageSrc = super.getImageSrc() + descriptionImgUrlById();
    }

    @Override
    public void saveImagesToFS(byte[] bytes) throws IOException {
        saveDescriptionImage(bytes);
    }

    private void saveDescriptionImage(byte[] bytes) throws IOException {
        String folderTitle = getFolderTitle() + "";
        String folderPath = getFolderPath();
        String imageTitle = getImageTitle(productId);
        makeDir(folderPath);
        //TODO remove testing
        String projectFolderPath = "/Users/volodymyrdudas/Desktop/WebShop/src/main/webapp/resources/img/descr/" + folderTitle + "/";
        makeDir(projectFolderPath);

        String filePath = folderPath + imageTitle;
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));

        ImageIO.write(image, IMAGE_EXTENSION, new File(filePath));
        ImageIO.write(image, IMAGE_EXTENSION, new File(projectFolderPath + imageTitle));
        this.hasImg = true;
    }

    @Override
    public void deleteImagesFromFS() {
        String fullPath = getFolderPath() + getImageTitle(productId);
        File file = new File(fullPath);
        file.delete();
        this.hasImg = false;
    }
}
