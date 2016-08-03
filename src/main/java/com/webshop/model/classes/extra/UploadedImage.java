package com.webshop.model.classes.extra;

import org.springframework.web.multipart.MultipartFile;

public class UploadedImage {

    private MultipartFile file;

    public UploadedImage() {
    }

    public UploadedImage(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
