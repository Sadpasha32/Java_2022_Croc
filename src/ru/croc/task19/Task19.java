package ru.croc.task19;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task19 {
    public static void main(String[] args) throws IOException {
        String filePath = System.getProperty("user.home") + "/Desktop/HelloWorld.txt";
        Path textFilePath = Paths.get(filePath);
        Files.createFile(textFilePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("Hello World!");
        writer.close();
    }
}
