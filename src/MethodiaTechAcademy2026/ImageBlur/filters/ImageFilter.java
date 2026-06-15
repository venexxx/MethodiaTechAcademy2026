package MethodiaTechAcademy2026.ImageBlur.filters;

import java.awt.image.BufferedImage;

public interface ImageFilter {

    BufferedImage apply(BufferedImage input);
    String getName();
}
