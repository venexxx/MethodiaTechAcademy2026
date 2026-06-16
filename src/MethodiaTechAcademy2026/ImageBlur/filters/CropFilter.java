package MethodiaTechAcademy2026.ImageBlur.filters;

import java.awt.image.BufferedImage;

public class CropFilter implements ImageFilter {

    private static int x;
    private static int y;
    private static int width;
    private static int height;

    public CropFilter(int with, int heigh, int x, int y) {
        this.x = x;
        this.y = y;
        this.width = with;
        this.height = heigh;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        return input.getSubimage(x, y, width, height);
    }

    @Override
    public String getName() {
        return "Crop Filter";
    }
}
