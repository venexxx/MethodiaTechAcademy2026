package MethodiaTechAcademy2026.ImageBlur;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class ImageBlur {


    public static void main(String[] args) {
        String inputPath = "src/MethodiaTechAcademy2026/ImageBlur/img/testBlur1.jpeg";
        String outputPath = "src/MethodiaTechAcademy2026/ImageBlur/img/output.jpeg";

        try {
            BufferedImage inputImage = ImageIO.read(new File(inputPath));

            if (inputImage == null) {
                System.out.println("Invalid input!");
                return;
            }

            BufferedImage outputImage = inputImage;

            ImageIO.write(outputImage, "jpg", new File(outputPath));

            System.out.println("Saved!: " + outputPath);

        } catch (IOException e) {
            System.out.println("Input Output Error!" + e.getMessage());
        }
    }

}