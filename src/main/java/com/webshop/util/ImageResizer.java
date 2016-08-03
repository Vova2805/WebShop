package com.webshop.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ImageResizer {

    /**
     * This method return resized image as a byte array.
     *
     * @param image  image represented as a byte array
     * @param width  desired width in pixels
     * @param height desired height in pixels
     * @return resized image as a byte array
     * @throws IOException
     */
    public static BufferedImage resizeImage(byte[] image, int width, int height) throws IOException {
        BufferedImage newImage = getScaledInstance
                (ImageIO.read(new ByteArrayInputStream(image)), width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
        return newImage;
    }


    /**
     * Convenience method that returns a scaled instance of the
     * provided {@code BufferedImage}.
     *
     * @param img          the original image to be scaled
     * @param targetWidth  the desired width of the scaled instance,
     *                     in pixels
     * @param targetHeight the desired height of the scaled instance,
     *                     in pixels
     * @param hint         one of the rendering hints that corresponds to
     *                     {@code RenderingHints.KEY_INTERPOLATION} (e.g.
     *                     {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
     *                     {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
     *                     {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
     */
    private static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {

        int type = (img.getTransparency() == Transparency.OPAQUE) ?
                BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = img;

        //scale without cutting images

        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        double widthFactor = (double) targetWidth / imgWidth;
        double heightFactor = (double) targetHeight / imgHeight;

        if (widthFactor > heightFactor) {
            heightFactor = widthFactor;
        } else {
            widthFactor = heightFactor;
        }
        targetWidth = (int) (imgWidth * widthFactor);
        targetHeight = (int) (imgHeight * heightFactor);

        if (imgWidth < targetWidth || imgHeight < targetHeight) {
            imgWidth = targetWidth;
            imgHeight = targetHeight;
            BufferedImage tmp = new BufferedImage(imgWidth, imgHeight, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setColor(Color.WHITE);

            //interpolation for best quality
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, imgWidth, imgHeight, null);
            g2.dispose();

            ret = tmp;
        } else {
            while (imgWidth != targetWidth || imgHeight != targetHeight) {
                if (imgWidth > targetWidth) {
                    imgWidth /= 2;
                    if (imgWidth < targetWidth) {
                        imgWidth = targetWidth;
                    }
                }

                if (imgHeight > targetHeight) {
                    imgHeight /= 2;
                    if (imgHeight < targetHeight) {
                        imgHeight = targetHeight;
                    }
                }
                BufferedImage tmp = new BufferedImage(imgWidth, imgHeight, type);
                Graphics2D g2 = tmp.createGraphics();
                g2.setColor(Color.WHITE);
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
                g2.drawImage(ret, 0, 0, imgWidth, imgHeight, null);
                g2.dispose();

                ret = tmp;
            }
        }
        return ret;
    }
}