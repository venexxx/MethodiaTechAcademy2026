package MethodiaTechAcademy2026.ImageBlur.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            System.out.println("Invalid file name!");
            return null;
        }

        return fileName.substring(dotIndex + 1).toLowerCase();
    }

    public static BufferedImage prepareForSaving(BufferedImage image, String format) {
        if (format.equalsIgnoreCase("jpg") || format.equalsIgnoreCase("jpeg")) {
            BufferedImage rgbImage = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );

            Graphics2D g2d = rgbImage.createGraphics();
            g2d.drawImage(image, 0, 0, Color.WHITE, null);
            g2d.dispose();

            return rgbImage;
        }

        return image;
    }
}
