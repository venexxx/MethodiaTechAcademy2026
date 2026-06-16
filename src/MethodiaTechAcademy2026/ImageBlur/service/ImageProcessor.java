package MethodiaTechAcademy2026.ImageBlur.service;


import MethodiaTechAcademy2026.ImageBlur.filters.ImageFilter;

import java.awt.image.BufferedImage;
import java.util.List;

public class ImageProcessor {
    public static BufferedImage process(BufferedImage inputImage, List<ImageFilter> filters) {
        //ImageFilter filter = FilterFactory.createImageFilter(filterType, radius,channel,x,y,width,height);

        BufferedImage outputImage = inputImage;
        for( ImageFilter filter : filters ){
            outputImage = filter.apply(outputImage);
        }

        return outputImage;
    }
}
