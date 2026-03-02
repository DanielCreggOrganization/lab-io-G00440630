package ie.atu.iolab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path inputPath = Paths.get("resources", "input.txt");
        Path outputPath = Paths.get("resources", "output.txt");
        int data;
        
        try (FileInputStream fis = new FileInputStream(inputPath.toFile());
             FileOutputStream fos = new FileOutputStream(outputPath.toFile())) {
            while ((data = fis.read()) != -1) {
                char c = (char) data;
                char toWrite;
                // only uppercase the letters T, H, I, S (regardless of case in input)
                if ("THIS".indexOf(Character.toUpperCase(c)) >= 0) {
                    toWrite = Character.toUpperCase(c);
                } else {
                    toWrite = Character.toLowerCase(c);
                }
                fos.write(toWrite);
            }
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
        System.out.println("File processed successfully.");

        // now read the result file and print its contents to the console
        try (java.io.FileInputStream fis2 = new java.io.FileInputStream(outputPath.toFile())) {
            int b;
            System.out.println("\nContents of " + outputPath + ":");
            while ((b = fis2.read()) != -1) {
                System.out.print((char)b);
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error reading output file: " + e.getMessage());
        }
    }
}