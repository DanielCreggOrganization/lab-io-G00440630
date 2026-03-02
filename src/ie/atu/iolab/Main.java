package ie.atu.iolab;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("resources", "nio_example.txt");
        String readContent = "";
        System.out.println("+-----------------------------+");
        try {
            // Write a simple string directly to the file without needing Streams!
            Files.writeString(path, "What is NIO? It stands for " + "\nNew I/O, and it's a powerful " + "\nway to handle file operations" + "\nin Java. It offers better " + "\nperformance and more features " + "\nthan the old java.io package.");
            // Read the entire content back into memory in one swoop
            readContent = Files.readString(path);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        
        // Print outside the try block
        System.out.println(readContent);
        System.out.println("+-----------------------------+");
    }
}