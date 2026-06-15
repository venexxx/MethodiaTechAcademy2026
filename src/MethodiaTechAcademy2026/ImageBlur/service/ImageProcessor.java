package MethodiaTechAcademy2026.ImageBlur.service;

import MethodiaTechAcademy2026.ImageBlur.enums.FilterType;
import MethodiaTechAcademy2026.ImageBlur.factory.FilterFactory;
import MethodiaTechAcademy2026.ImageBlur.filters.ImageFilter;

import java.awt.image.BufferedImage;

public class ImageProcessor {

    public static BufferedImage process(BufferedImage inputImage, FilterType filterType, int radius) {
        ImageFilter filter = FilterFactory.createImageFilter(filterType, radius);
        return filter.apply(inputImage);
    }
}
