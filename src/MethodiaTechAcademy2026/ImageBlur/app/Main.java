package MethodiaTechAcademy2026.ImageBlur.app;

import MethodiaTechAcademy2026.ImageBlur.enums.ColourChanel;
import MethodiaTechAcademy2026.ImageBlur.enums.FilterType;
import MethodiaTechAcademy2026.ImageBlur.filters.*;
import MethodiaTechAcademy2026.ImageBlur.service.ImageProcessor;
import MethodiaTechAcademy2026.ImageBlur.util.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String inputPath = readInputPath(args);
        BufferedImage inputImage = loadImage(inputPath);
        List<ImageFilter> filters = parseFilters(args);
        ImageProcessor processor = new ImageProcessor();
        BufferedImage outputImage = processor.process(inputImage,filters);
        saveImage(outputImage, inputPath);



//

//        String filterType = readFilterType(args);
//
//        ColourChanel colorChannel = readColourChanel(args);
//
//        int radius = readRadius(args);
//
//        List<Integer> cropParams = readCropParams(args);
//

        //List<FilterType> types = loadFiltersArgs(filterType,radius,colorChannel,cropParams);






       // outputImage = processor.process(outputImage, FilterType.COLOUR_FILTER, radius, colorChannel);
       // saveImage(outputImage, inputPath);



    }

    private static List<ImageFilter> parseFilters(String[] args) {
        List<ImageFilter> filters = new ArrayList<>();

        int i = 1;

        while (i < args.length) {
            String command = args[i].toLowerCase();

            switch (command) {
                case "averagebrightnessblur":
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Missing radius for averagebrightnessblur");
                    }

                    int averageRadius = Integer.parseInt(args[i + 1]);
                    filters.add(new AverageBrightnessBlurFilter(averageRadius));
                    i += 2;
                    break;

                case "medianblur":
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Missing radius for medianblur");
                    }

                    int medianRadius = Integer.parseInt(args[i + 1]);
                    filters.add(new MedianBlurFilter(medianRadius));
                    i += 2;
                    break;

                case "colorfilter":
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Missing color for colorfilter");
                    }

                   ColourChanel channel = ColourChanel.valueOf(args[i + 1].toUpperCase());
                    filters.add(new ColourFilter(channel));
                    i += 2;
                    break;

                case "crop":
                    if (i + 4 >= args.length) {
                        throw new IllegalArgumentException("Missing parameters for crop");
                    }

                    int x = Integer.parseInt(args[i + 1]);
                    int y = Integer.parseInt(args[i + 2]);
                    int width = Integer.parseInt(args[i + 3]);
                    int height = Integer.parseInt(args[i + 4]);

                    filters.add(new CropFilter(width, height,x,y));
                    i += 5;
                    break;

                default:
                    throw new IllegalArgumentException("Unknown command: " + args[i]);
            }
        }

        return filters;
    }

//    private static List<Integer> readCropParams(String[] args) {
//        int x = Integer.parseInt(args[5]);
//        int y = Integer.parseInt(args[6]);
//
//        int width = Integer.parseInt(args[7]);
//        int height = Integer.parseInt(args[8]);
//
//        List<Integer> cropParams =List.of(x,y,width,height);
//        return cropParams;
//    }
//
//    private static List<FilterType> loadFiltersArgs() {
//
//    }
//
//    private static ColourChanel readColourChanel(String[] args) {
//        String colourChanel = args[3];
//        String colour = args[4];
//        ColourChanel chanel = ColourChanel.valueOf(colour.toUpperCase());
//        return chanel;
//    }
//
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

    private static int readRadius(String[] args) {
        return Integer.parseInt(args[2]);
    }
//
//    private static FilterType readFilterType(String[] args) {
//        String filterType = args[1];
//        return switch (filterType) {
//            case "Median" -> FilterType.MEDIAN;
//            case "AverageBrightness" -> FilterType.AVERAGE_BRIGHTNESS;
//            case "colorfilter" -> FilterType.COLOUR_FILTER;
//            case "crop" -> FilterType.CROP_FILTER;
//            default -> throw new IllegalArgumentException("Invalid filter type!");
//        };
//
//    }

    private static String readInputPath(String[] args) {
        return  args[0];
       //TODO: HTTPS-URL return  "https://as2.ftcdn.net/v2/jpg/09/59/54/27/1000_F_959542727_JGtEhgcqeXbpBKygqxPdODBpNAIM5P8L.jpg";
    }
}
