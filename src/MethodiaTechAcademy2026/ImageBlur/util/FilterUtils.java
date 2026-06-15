package MethodiaTechAcademy2026.ImageBlur.util;

public class FilterUtils {


    public static int getWindowSize(int radius){
        return (2 * radius + 1) * (2 * radius + 1);
    }
}
