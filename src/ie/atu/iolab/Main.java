package ie.atu.iolab;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        Path projectRoot = Paths.get(System.getProperty("user.dir"));
        Path inputFilePath = projectRoot.resolve("resources").resolve("input.txt");

        if (Files.exists(inputFilePath)) {
            System.out.println("input.txt found at: " + inputFilePath.toAbsolutePath());
        } else {
            System.out.println("input.txt not found at: " + inputFilePath.toAbsolutePath());
        }
        System.out.println();
        Path outputFilePath = projectRoot.resolve("resources").resolve("output.txt");

        if (Files.exists(outputFilePath)) {
            System.out.println("output.txt found at: " + outputFilePath.toAbsolutePath());
        } else {
            System.out.println("output.txt not found at: " + outputFilePath.toAbsolutePath());
        }
    }
}