package com.webshop.model.classes.extra;

import com.webshop.model.enums.ImageSize;
import com.webshop.util.ImageResizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contais general info about image and methods for overriding
 */
public abstract class ImageDecorator implements IImage {

    public static final transient String IMAGE_EXTENSION = "png";
    //identify how much product images can be in one folder
    //by default product images have three sizes. Thus 300 is enough
    protected static final transient int MAX_GOODS_IN_FOLDER = 300;

    //each product have description. Description contains parts. Each part can have image
    protected static final transient int MAX_DESCR_IN_FOLDER = 1000;

    //default upload location
    protected static final transient String UPLOAD_LOCATION = System.getProperty("catalina.home") + "/webapps/ROOT/resources/img/";

    protected static final String getBaseImgURL() {
        return "resources/img/";
    }

    protected static final String getRealBaseImgURL() {
        return UPLOAD_LOCATION;
    }

    public static String getExtension() {
        return "." + IMAGE_EXTENSION;
    }

    public static String getExtension(ImageSize imageSize) {
        return "_" + imageSize + getExtension();
    }

    /**
     * Checking for existed file
     *
     * @param realImagePath
     * @return
     */
    public static boolean isImageExists(String realImagePath) {
        File file = new File(realImagePath);
        return file.exists();
    }

    /**
     * Checking image for existing for each extensions (sizes)
     *
     * @param realImagePath
     * @param extensions
     * @return
     */
    public static boolean isImageExists(String realImagePath, List<ImageSize> extensions) {
        boolean result = true;

        for (ImageSize size : extensions) {
            String fullPath = realImagePath + getExtension(size);
            File file = new File(fullPath);
            result &= file.exists();
        }
        return result;
    }

    /**
     * Function for making directory
     *
     * @param path
     */
    protected static void makeDir(String path) {
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
    }

    public String getImageSrc() {
        return getBaseImgURL();
    }

    /**
     * Default base url for categories
     *
     * @param categoryId
     * @return
     */
    protected final String categoryImgBaseUrl(int categoryId) {
        return "units/" + categoryId + "/";
    }

    /**
     * Iniializing fields by full path to images
     *
     * @param sizes
     * @return
     */
    protected Map<String, String> initializeImages(List<ImageSize> sizes) {
        Map<String, String> images = new HashMap<>();
        sizes.forEach(imageSize -> {
            images.put(imageSize.toString(), getImageSrc() + getExtension(imageSize));
        });
        return images;
    }

    /**
     * General function for recording image to the file system
     *
     * @param bytes
     * @param foldersPath
     * @param imageTitle
     * @param sizes
     * @throws IOException
     */
    protected void saveImagesToFS(byte[] bytes, String foldersPath, String imageTitle, List<ImageSize> sizes) throws IOException {
        if (!(new File(getRealBaseImgURL() + foldersPath).exists())) {
            makeDir(getRealBaseImgURL() + foldersPath);
        }

        for (ImageSize size : sizes) {
            String filePath = getRealBaseImgURL() + foldersPath + imageTitle + getExtension(size);
            BufferedImage image = ImageResizer.resizeImage(bytes, size.getWidth(), size.getHeight());

            ImageIO.write(image, IMAGE_EXTENSION, new File(filePath));
        }
    }

    /**
     * Deleting image from the file system
     *
     * @param fullPath
     * @param sizes
     */
    protected void deleteImagesFromFS(String fullPath, List<ImageSize> sizes) {
        //this is default image
        if (fullPath.contains("res/")) return;
        //if not
        for (ImageSize size : sizes) {
            String path = getRealBaseImgURL() + fullPath + getExtension(size);
            File file = new File(path);
            file.delete();
        }
    }

}
