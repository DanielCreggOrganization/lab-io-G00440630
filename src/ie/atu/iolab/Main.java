package ie.atu.iolab;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Define the path to the file we want to read
        Path filePath = Paths.get("resources", "input.txt");
        int byteFromFile;  // Variable to hold the current byte being read
        // Track the amount of bytes read
        int byteCount = 0;
        int charCount = 0;
        // try-with-resources ensures the FileInputStream is closed automatically.
        // We use .toFile() to convert the NIO Path object to a standard Java File object.
        try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
            // read() reads one byte of data from the input stream. 
            // It returns -1 when there is no more data (End Of File).
            while ((byteFromFile = fis.read()) != -1) {
                // Casting the byte to a char works here because we're using basic ASCII text.
                // For advanced encodings (like UTF-8), a Reader should be used instead.
                System.out.print((byte) byteFromFile); 
                byteCount++;
                System.out.print((char) byteFromFile); 
                charCount++;
            }
        } catch (IOException e) {
            // IOException acts as a catch-all for stream errors,
            // including FileNotFoundException.
            System.err.println("Error reading file: " + e.getMessage());
        }
        System.out.println("\nTotal bytes: " + byteCount);
        System.out.println("\nTotal characters: " + charCount);
    }
}