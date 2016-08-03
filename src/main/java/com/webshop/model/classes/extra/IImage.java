package com.webshop.model.classes.extra;

import java.io.IOException;

public interface IImage {

    String getImageSrc();

    void initializeImages();

    /**
     * Work with FS
     *
     * @param bytes
     * @throws IOException
     */
    void saveImagesToFS(byte[] bytes) throws IOException;

    void deleteImagesFromFS();
}
