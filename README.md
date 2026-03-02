[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=22930702)
# Java I/O Lab

This lab introduces you to Java Input/Output (I/O) operations, covering file handling, reading, writing, and using streams.

---

## Agenda

1.  [Project Setup](#1-project-setup)
2.  [Creating and Locating Files](#2-creating-and-locating-files)
3.  [Reading with Input Streams](#3-reading-with-input-streams)
4.  [Writing with Output Streams](#4-writing-with-output-streams)
5.  [Character-based I/O with Readers and Writers](#5-character-based-io-with-readers-and-writers)
6.  [Buffered I/O Operations](#6-buffered-io-operations)
7.  [Simple File I/O with Java NIO `Files` Utility](#7-simple-file-io-with-java-nio-files-utility)

---

## 1. Project Setup

Before you begin, create the necessary project structure:

1.  **Create a Package:** Inside the `src` folder of your project, create a new package named `ie.atu.iolab`. This helps organize your code.
2.  **Create a `Main` Class:** Within the `ie.atu.iolab` package, create a new Java class named `Main`. This class will contain your `main` method.
3.  **Create a `resources` Folder:** *At the same level as your `src` folder* (i.e., in the project's root directory), create a new folder named `resources`. This is where you'll store text files used in the lab. **Important:** Do *not* create the `resources` folder inside the `src` folder.
4.  **Add a test print:** Add to the main method to ensure your environment is setup:

    ```java
    package ie.atu.iolab;

    public class Main {
        public static void main(String[] args) {
            System.out.println("Java I/O Lab Setup Complete!");
        }
    }
    ```
5.  **Run the `Main` Class:** Run your `Main` class. You should see "Java I/O Lab Setup Complete!" printed in the console. This confirms your project is set up correctly.

---

## 2. Creating and Locating Files

### Concept Introduction

Java provides robust file handling capabilities through the `java.io` and `java.nio.file` packages.  The `java.nio.file` package offers a modern approach.  The `Path` interface represents file and directory paths, and the `Files` class provides utility methods.

```mermaid
graph LR
    A[Java Application] --> B[java.io]
    A --> C[java.nio.file]
    B --> D[File handling]
    C --> D
    D --> E[Create files]
    D --> F[Locate files]
    D --> G[Manipulate files]
```

### Code Example

```java
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
    }
}
```

<details>
<summary>Expected Output (File Found)</summary>

```
input.txt found at: /path/to/your/project/JavaIOLab/resources/input.txt
```
*Note: The exact path will depend on your project location.*
</details>

<details>
<summary>Expected Output (File Not Found)</summary>

```
input.txt not found at: /path/to/your/project/JavaIOLab/resources/input.txt
```
*Note: The exact path will depend on your project location.*
</details>

### DIY Task

1.  **Create `input.txt`:** In the `resources` folder, create a new text file named `input.txt`.
2.  **Add Content:** Add the line "Hello, Java I/O!" to `input.txt`.
3.  **Modify the Code:** Change the code to check for and print the path of a file named `output.txt` *instead* of `input.txt`. *Do not create `output.txt` yet*.
4.  **Run and Observe:** Run the program. The output should indicate that `output.txt` is *not* found.
5.  **Create `output.txt`:** Now, create an empty `output.txt` file in the `resources` folder.
6.  **Run Again:** Run the program again. This time, it should find and print the path to `output.txt`.

---

## 3. Reading with Input Streams

### Concept Introduction

Input streams in Java read data from a source (file, network, etc.) as *bytes*. The base class is `java.io.InputStream`. `FileInputStream` is used for reading bytes from files.

```mermaid
graph TD
    A[InputStream] --> B[FileInputStream]
    A --> C[ByteArrayInputStream]
    B --> E[Read from files]
    C --> F[Read from memory]
```

### Code Example

```java
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
        int charCount = 0; // Track the amount of characters read

        // try-with-resources ensures the FileInputStream is closed automatically.
        // We use .toFile() to convert the NIO Path object to a standard Java File object.
        try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
            // read() reads one byte of data from the input stream. 
            // It returns -1 when there is no more data (End Of File).
            while ((byteFromFile = fis.read()) != -1) {
                // Casting the byte to a char works here because we're using basic ASCII text.
                // For advanced encodings (like UTF-8), a Reader should be used instead.
                System.out.print((char) byteFromFile); 
                charCount++;
            }
        } catch (IOException e) {
            // IOException acts as a catch-all for stream errors,
            // including FileNotFoundException.
            System.err.println("Error reading file: " + e.getMessage());
        }
        System.out.println("\nTotal characters: " + charCount);
    }
}
```

<details>
<summary>Expected Output (with "Hello, Java I/O!" in input.txt)</summary>

```
Hello, Java I/O!
Total characters: 16
```
</details>

### DIY Task

1.  **Start with the Example:** Copy the example code into your `Main` class.
2.  **Modify `input.txt`:** Change the content of `input.txt` to "This is a different test string.". Save the file.
3.  **Modify the Code:** *Without changing the file reading logic*, modify the program to print the number of *bytes* read, in addition to the characters. The number of bytes read should be the same as the character count in this case (because we're using ASCII characters). Add a separate print statement for the byte count.
4.  **Run and Verify:** Run your program. Verify that it prints the new content of `input.txt` and that the character count and byte count are both correct and equal.

<details>
<summary>Expected Output (After DIY Task)</summary>

```
This is a different test string.
Total characters: 32
Total bytes: 32
```
*Note: Your output messages might be slightly different, but the counts should be 32.*
</details>

---

## 4. Writing with Output Streams

### Concept Introduction

Output streams write data to a destination (file, network, etc.) as *bytes*.  The base class is `java.io.OutputStream`. `FileOutputStream` writes bytes to files.

```mermaid
graph TD
    A[OutputStream] --> B[FileOutputStream]
    A --> C[ByteArrayOutputStream]
    A --> D[Other specialized streams...]
    B --> E[Write to files]
    C --> F[Write to memory]
```

### Code Example

```java
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
                fos.write(Character.toUpperCase((char)data));
            }
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
        System.out.println("File copied successfully.");
    }
}
```

<details>
<summary>Expected Output</summary>
File copied successfully.
</details>

### DIY Task

1.  **Start with the Example:** Copy the example code into your `Main` class.
2.  **Modify `input.txt`:** Change the content of `input.txt` to "write THIS to output.txt".
3.  **Modify the Code:** Instead of converting the *entire* input to uppercase, modify the code to write only the characters 'T', 'H', 'I', and 'S' (from the word "THIS") in uppercase to `output.txt`. All other characters should be written in lowercase.
4.  **Run and Verify:** Run your program. Check the content of `output.txt`.

<details>
<summary>Expected Content of output.txt</summary>
wriTE THIS To ouTPuT.TxT
</details>

---

## 5. Character-based I/O with Readers and Writers

### Concept Introduction

Readers and Writers handle *character* data, making them suitable for text. They handle character encoding. `FileReader` and `FileWriter` are convenient for text files.

```mermaid
graph TD
    A[Reader] --> B[FileReader]
    A --> C[StringReader]
    D[Writer] --> E[FileWriter]
    D --> F[StringWriter]
    B --> G[Read text from files]
    E --> H[Write text to files]
```

### Code Example

```java
package ie.atu.iolab;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path inputPath = Paths.get("resources", "input.txt");
        Path outputPath = Paths.get("resources", "output.txt");
        int character;

        try (FileReader reader = new FileReader(inputPath.toFile());
             FileWriter writer = new FileWriter(outputPath.toFile())) {
            while ((character = reader.read()) != -1) {
                writer.write(character); // Write the character directly
            }
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}
```

<details>
<summary>Expected Output (Console)</summary>
File copied successfully.
</details>

### DIY Task

1.  **Start with the Example:** Copy the *simplified* example code (the one without vowel counting) into your `Main` class.
2.  **Modify `input.txt`:** Change the content of `input.txt` to "Readers and Writers are cool!".
3.  **Modify the Code:** Modify the program to *reverse* the content of `input.txt` and write the reversed string to `output.txt`. You'll need to store the characters as you read them, and then write them in reverse order. (Hint: A `StringBuilder` is very useful for this.)
4.  **Run and Verify:** Check the contents of `output.txt`.

<details>
<summary>Expected Content of output.txt</summary>
!looc era sretirW dna sraedeR
</details>

---

## 6. Buffered I/O Operations

### Concept Introduction

`BufferedReader` and `BufferedWriter` improve performance by reading/writing chunks of data to an internal buffer, reducing system calls.

```mermaid
graph TD
    A[BufferedReader] --> B[FileReader]
    C[BufferedWriter] --> D[FileWriter]
    A --> E[Read line by line]
    C --> F[Write with buffering]
    B --> G[Underlying file]
    D --> G
```

### Code Example

```java
package ie.atu.iolab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path inputPath = Paths.get("resources", "input.txt");
        Path outputPath = Paths.get("resources", "output.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath.toFile()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toFile()))) {

            String line;
            while ((line = reader.readLine()) != null) { // Read line by line
                writer.write(line.toUpperCase());
                writer.newLine(); // Add a newline after each line
            }
            System.out.println("File processed with buffered I/O.");
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}
```
<details>
<summary>Expected Output (Console)</summary>

```
File processed with buffered I/O.
```
</details>

### DIY Task

1.  **Start with the Example:** Copy the example code into your `Main` class.
2.  **Modify `input.txt`:** Change the content of `input.txt` to:
    ```
    This is the first line.
    This is the second line.
    And this is the third.
    ```
3.  **Modify the Code:** Modify the program to read the input file line by line, but only write lines that contain the word "second" to `output.txt`.
4.  **Run and Verify:** Check `output.txt`.

<details>
<summary>Expected Content of output.txt</summary>

```
THIS IS THE SECOND LINE.
```
</details>

---

## 7. Simple File I/O with Java NIO `Files` Utility

### Concept Introduction

The `java.nio.file.Files` class (introduced in Java 7 and expanded in Java 11) provides incredibly simple, one-line utility methods to handle basic read and write operations. Rather than managing complex Stream, Reader, or Writer objects yourself, you can hand off a `Path` to the `Files` class and let it handle everything in the background.

```mermaid
graph TD
    A[Java Application] --> B["Files Utility Class"]
    B --> C["writeString()"]
    B --> D["readString()"]
    C --> E[Underlying File]
    D --> E
```

### Code Example

```java
package ie.atu.iolab;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("resources", "nio_example.txt");
        String readContent = "";

        try {
            // Write a simple string directly to the file without needing Streams!
            Files.writeString(path, "Hello, NIO!");
            // Read the entire content back into memory in one swoop
            readContent = Files.readString(path);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        
        // Print outside the try block
        System.out.println(readContent);
    }
}
```

<details>
<summary>Expected Output (Console)</summary>

```
Hello, NIO!
```
</details>

### DIY Task

1.  **Start with the Example:** Copy the example code into your `Main` class.
2.  **Modify the Path:** Change the script to output to `nio_task.txt` inside your resources folder instead of `nio_example.txt`.
3.  **Modify the Code:** Update the file write payload. Instead of writing "Hello, NIO!", concatenate two lines together using standard `\n` characters (e.g. "This is line 1\nThis is line 2"). 
4.  **Run and Verify:** Run the program and verify that the console output accurately parses and displays your multiline string with the correct newline break!

<details>
<summary>Expected Output</summary>

```
This is line 1
This is line 2
```
</details>

---

End of Lab
