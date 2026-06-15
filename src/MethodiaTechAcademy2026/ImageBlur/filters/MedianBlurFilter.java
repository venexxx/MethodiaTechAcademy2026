package MethodiaTechAcademy2026.ImageBlur.filters;

import MethodiaTechAcademy2026.ImageBlur.util.FilterUtils;
import MethodiaTechAcademy2026.ImageBlur.util.PixelUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MedianBlurFilter implements ImageFilter {


    private static int radius;

    public MedianBlurFilter(int radius) {
        this.radius = radius;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {

        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int windowSize = FilterUtils.getWindowSize(radius);

        int[] red = new int[windowSize];
        int[] green = new int[windowSize];
        int[] blue = new int[windowSize];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = 0;

                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int nx = PixelUtils.clamp(x + dx, 0, width - 1);
                        int ny = PixelUtils.clamp(y + dy, 0, height - 1);

                        int rgb = input.getRGB(nx, ny);

                        int r = PixelUtils.getRed(rgb);
                        int g = PixelUtils.getGreen(rgb);
                        int b = PixelUtils.getBlue(rgb);


                        red[index] = r;
                        green[index] = g;
                        blue[index] = b;



                        index++;
                    }
                }

                Arrays.sort(red);
                Arrays.sort(green);
                Arrays.sort(blue);

                int mid = windowSize / 2;

                int originalRgb = input.getRGB(x, y);
                int alpha = PixelUtils.getAlpha(originalRgb);

                int newRgb = PixelUtils.toRgb(alpha, red[mid], green[mid], blue[mid]);
                output.setRGB(x, y, newRgb);

                output.setRGB(x, y, newRgb);
            }
        }

        return output;
    }

    @Override
    public String getName() {
        return "median";
    }
}
