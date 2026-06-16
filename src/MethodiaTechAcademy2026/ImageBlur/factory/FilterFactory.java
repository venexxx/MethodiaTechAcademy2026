package MethodiaTechAcademy2026.ImageBlur.factory;

import MethodiaTechAcademy2026.ImageBlur.enums.ColourChanel;
import MethodiaTechAcademy2026.ImageBlur.enums.FilterType;
import MethodiaTechAcademy2026.ImageBlur.filters.*;

public class FilterFactory {

    public static ImageFilter createImageFilter(FilterType filterType, int radius, ColourChanel channel,int with,int heigh,int x,int y) {

        return switch (filterType) {
            case MEDIAN -> new MedianBlurFilter(radius);
            case AVERAGE_BRIGHTNESS -> new AverageBrightnessBlurFilter(radius);
            case COLOUR_FILTER ->  new ColourFilter(channel);
            case CROP_FILTER ->  new CropFilter(with,heigh,x,y);
        };
    }
}
