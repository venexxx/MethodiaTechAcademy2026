package MethodiaTechAcademy2026.ImageBlur.filters;

import MethodiaTechAcademy2026.ImageBlur.enums.ColourChanel;
import MethodiaTechAcademy2026.ImageBlur.util.PixelUtils;

import java.awt.image.BufferedImage;

public class ColourFilter implements ImageFilter{

    private static ColourChanel chanel;

    public ColourFilter(ColourChanel chanel) {
        this.chanel = chanel;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = input.getRGB(x, y);

                int red = PixelUtils.getRed(rgb);
                int green = PixelUtils.getGreen(rgb);
                int blue = PixelUtils.getBlue(rgb);
                int newRgb = 0;

                switch (chanel) {
                    case RED:
                        newRgb = red << 16;
                        break;
                    case GREEN:
                        newRgb = green << 8;
                        break;
                    case BLUE:
                        newRgb = blue;
                        break;
                }

                output.setRGB(x, y, newRgb);
            }
        }

        return output;

    }

    @Override
    public String getName() {
        return "Colour Filter";
    }
}
