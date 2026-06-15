package MethodiaTechAcademy2026.ImageBlur.factory;

import MethodiaTechAcademy2026.ImageBlur.enums.ColourChanel;
import MethodiaTechAcademy2026.ImageBlur.enums.FilterType;
import MethodiaTechAcademy2026.ImageBlur.filters.AverageBrightnessBlurFilter;
import MethodiaTechAcademy2026.ImageBlur.filters.ColourFilter;
import MethodiaTechAcademy2026.ImageBlur.filters.ImageFilter;
import MethodiaTechAcademy2026.ImageBlur.filters.MedianBlurFilter;

public class FilterFactory {

    public static ImageFilter createImageFilter(FilterType filterType, int radius, ColourChanel channel) {

        return switch (filterType) {
            case MEDIAN -> new MedianBlurFilter(radius);
            case AVERAGE_BRIGHTNESS -> new AverageBrightnessBlurFilter(radius);
            case COLOUR_FILTER ->  new ColourFilter(channel);
        };
    }
}
