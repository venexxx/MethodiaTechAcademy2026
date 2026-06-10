package MethodiaTechAcademy2026.ImageBlur;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
public class ImageBlur {


    public static void main(String[] args) {
        String inputPath = "src/MethodiaTechAcademy2026/ImageBlur/img/testBlur1.jpeg";
        String outputPath = "src/MethodiaTechAcademy2026/ImageBlur/img/output.";
        int radius = 10;


        try {
            BufferedImage inputImage = ImageIO.read(new File(inputPath));

            if (inputImage == null) {
                System.out.println("Invalid input!");
                return;
            }

            BufferedImage outputImage = applyMedianBlur(inputImage, radius);
            String format = getFileExtension(inputPath);

            if (format == null) {
                format = "jpg";
            }

            ImageIO.write(outputImage, format, new File(outputPath + format));

            System.out.println("Saved!: " + outputPath);


        } catch (IOException e) {
            System.out.println("Input Output Error!" + e.getMessage());
        }
    }

    public static BufferedImage applyMedianBlur(BufferedImage input, int radius) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, input.getType());

        int windowSize = (2 * radius + 1) * (2 * radius + 1);

        int[] red = new int[windowSize];
        int[] green = new int[windowSize];
        int[] blue = new int[windowSize];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = 0;

                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int nx = clamp(x + dx, 0, width - 1);
                        int ny = clamp(y + dy, 0, height - 1);

                        Color color = new Color(input.getRGB(nx, ny));

                        red[index] = color.getRed();
                        green[index] = color.getGreen();
                        blue[index] = color.getBlue();

                        index++;
                    }
                }

                Arrays.sort(red);
                Arrays.sort(green);
                Arrays.sort(blue);

                int mid = windowSize / 2;
                int r = red[mid];
                int g = green[mid];
                int b = blue[mid];

                Color newColor = new Color(r, g, b);
                int newRgb = newColor.getRGB();

                output.setRGB(x, y, newRgb);
            }
        }

        return output;
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }


    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            System.out.println("Invalid file name!");
            return null;
        }

        return fileName.substring(dotIndex + 1).toLowerCase();
    }


}