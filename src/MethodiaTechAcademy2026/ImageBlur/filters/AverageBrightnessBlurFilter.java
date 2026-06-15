package MethodiaTechAcademy2026.ImageBlur.filters;

import MethodiaTechAcademy2026.ImageBlur.util.FilterUtils;
import MethodiaTechAcademy2026.ImageBlur.util.PixelUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AverageBrightnessBlurFilter implements ImageFilter {


    private static int radius;

    public AverageBrightnessBlurFilter(int radius) {
        this.radius = radius;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int windowSize = FilterUtils.getWindowSize(radius);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int sumBrightness = 0;

                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int nx = PixelUtils.clamp(x + dx, 0, width - 1);
                        int ny = PixelUtils.clamp(y + dy, 0, height - 1);

                        int neighborRgb = input.getRGB(nx, ny);
                        int brightness = PixelUtils.getBrightness(neighborRgb);
                        sumBrightness += brightness;
                    }
                }

                int avgBrightness = sumBrightness / windowSize;
                int originalRgb = input.getRGB(x, y);
                int alpha = PixelUtils.getAlpha(originalRgb);
                int newRgb = PixelUtils.toRgb(alpha, avgBrightness, avgBrightness, avgBrightness);
                output.setRGB(x, y, newRgb);
            }
        }

        return output;
    }

    @Override
    public String getName() {
        return "Average Brightness Blur";
    }
}
