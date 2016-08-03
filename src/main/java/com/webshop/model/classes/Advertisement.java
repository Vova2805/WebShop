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
public class Advertisement extends ImageDecorator {
    public static transient List<ImageSize> supportedSizes;

    static {
        supportedSizes = new ArrayList<ImageSize>() {{
            add(ImageSize.HUGE);
        }};
    }

    @Expose
    private int advertisementId;
    @Expose
    private String title;
    @Expose
    private String body;
    @Expose
    private String buttonHref;
    @Expose
    private Map<String, String> images;

    public Advertisement() {
        this.title = "";
        this.body = "";
        this.buttonHref = "/";
    }

    public Advertisement(String title, String body) {
        this.title = title;
        this.body = body;
        this.buttonHref = "/";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisementId", nullable = false)
    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 60)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "body", nullable = false)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "buttonHref", nullable = false)
    public String getButtonHref() {
        return buttonHref;
    }

    public void setButtonHref(String buttonHref) {
        this.buttonHref = buttonHref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertisement that = (Advertisement) o;

        if (advertisementId != that.advertisementId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (buttonHref != null ? !buttonHref.equals(that.buttonHref) : that.buttonHref != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = advertisementId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (buttonHref != null ? buttonHref.hashCode() : 0);
        return result;
    }

    private String AdvertFolderUrl() {
        return "slider/";
    }

    private String AdvertImageTitle() {
        return advertisementId + "";
    }

    @Override
    public void initializeImages() {
        images = initializeImages(supportedSizes);
    }

    @Override
    public void saveImagesToFS(byte[] bytes) throws IOException {
        saveImagesToFS(bytes, AdvertFolderUrl(), AdvertImageTitle(), supportedSizes);
    }

    @Override
    public void deleteImagesFromFS() {
        deleteImagesFromFS(AdvertFolderUrl() + AdvertImageTitle(), supportedSizes);
    }

    @Transient
    public Map<String, String> getImages() {
        if (images == null) initializeImages();
        return images;
    }

    @Transient
    @Override
    public String getImageSrc() {
        String src = super.getImageSrc() + AdvertFolderUrl() + AdvertImageTitle();
        boolean isExists = isImageExists(getRealBaseImgURL() + AdvertFolderUrl() + AdvertImageTitle(), supportedSizes);
        if (!isExists)
            src = super.getImageSrc() + getDefaultAdvPath();
        return src;
    }

    @Transient
    private String getDefaultAdvPath() {
        return "res/advDefault";
    }

}
