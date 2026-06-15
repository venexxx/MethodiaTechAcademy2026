package MethodiaTechAcademy2026.ImageBlur.app;

import MethodiaTechAcademy2026.ImageBlur.enums.ColourChanel;
import MethodiaTechAcademy2026.ImageBlur.enums.FilterType;
import MethodiaTechAcademy2026.ImageBlur.service.ImageProcessor;
import MethodiaTechAcademy2026.ImageBlur.util.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String inputPath = readInputPath(scanner);
        FilterType filterType = readFilterType(scanner);
        ColourChanel colorChannel = readColourChanel(scanner);
        int radius = readRadius(scanner);

        BufferedImage inputImage = loadImage(inputPath);

        ImageProcessor processor = new ImageProcessor();
        BufferedImage outputImage = processor.process(inputImage, filterType, radius, colorChannel);


        saveImage(outputImage, inputPath);
        outputImage = processor.process(outputImage, FilterType.COLOUR_FILTER, radius, colorChannel);
        saveImage(outputImage, inputPath);



    }

    private static ColourChanel readColourChanel(Scanner scanner) {
        System.out.println("Colour Chanel: ");
        String colourChanel = scanner.nextLine();
        ColourChanel chanel = ColourChanel.valueOf(colourChanel.toUpperCase());
        return chanel;
    }

    private static void saveImage(BufferedImage outputImage, String inputPath) {
        String outputPath = "src/MethodiaTechAcademy2026/ImageBlur/img/output.";
        try {
            String format = ImageUtils.getFileExtension(inputPath);
            BufferedImage imageToSave =ImageUtils.prepareForSaving(outputImage, format);

            File outputFile = new File(outputPath + format);

            boolean flag = ImageIO.write(imageToSave, format, outputFile);

            System.out.println(flag);

            System.out.println("Saved!: " + outputFile.getPath());


        } catch (IOException e) {
            System.out.println("Input Output Error!" + e.getMessage());
        }
    }

    private static BufferedImage loadImage(String inputPath) {
        BufferedImage inputImage = null;
        try {
            inputImage = ImageIO.read(new File(inputPath));

            if (inputImage == null) {
                throw new IOException("Invalid input path!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return inputImage;
    }

    private static int readRadius(Scanner scanner) {
        System.out.print("Enter radius: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static FilterType readFilterType(Scanner scanner) {
        System.out.println("Choose filter Median/AverageBrightness");
        String filterType = scanner.nextLine();
        return switch (filterType) {
            case "Median" -> FilterType.MEDIAN;
            case "AverageBrightness" -> FilterType.AVERAGE_BRIGHTNESS;
            default -> throw new IllegalArgumentException("Invalid filter type!");
        };

    }

    private static String readInputPath(Scanner scanner) {
        return  "src/MethodiaTechAcademy2026/ImageBlur/img/testBlur1.jpeg";
       //TODO: HTTPS-URL return  "https://as2.ftcdn.net/v2/jpg/09/59/54/27/1000_F_959542727_JGtEhgcqeXbpBKygqxPdODBpNAIM5P8L.jpg";
    }
}
