package ru.croc.task16;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task16 {
    public static void main(String[] args) {
        try (Stream<Path> paths = Files.walk(Paths.get(args[0]))) {
            List<String> logFiles = paths.map(Path::toString)
                    .filter(x -> x.toLowerCase().endsWith(".log") || x.toLowerCase().endsWith(".trace"))
                    .collect(Collectors.toList());
            int numberOfMerges = 0;
            String newFilePath = logFiles.get(logFiles.size() - 1);
            while (logFiles.size() != 1) {
                newFilePath = merge2Files(logFiles.get(0), logFiles.get(1), args[0], numberOfMerges);
                numberOfMerges++;
                logFiles.remove(0);
                logFiles.remove(0);
                logFiles.add(newFilePath);
            }
            FileInputStream fileStream = new FileInputStream(newFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
            String resLine;
            while ((resLine = reader.readLine()) != null) {
                System.out.println(resLine);
            }
            fileStream.close();
            reader.close();
            Path deletePath;
            while (numberOfMerges != -1) {
                deletePath = Paths.get(args[0] + "tempFile" + numberOfMerges + ".txt");
                Files.deleteIfExists(deletePath);
                numberOfMerges--;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String merge2Files(String file1, String file2, String directoryPath, int numberOfMerges) throws IOException {
        FileInputStream fileStream1 = new FileInputStream(file1);
        FileInputStream fileStream2 = new FileInputStream(file2);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(fileStream1));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fileStream2));
        String mergedFile = directoryPath + "tempFile" + numberOfMerges + ".txt";
        Path mergedFilePath = Paths.get(directoryPath + "tempFile" + numberOfMerges + ".txt");
        Files.createFile(mergedFilePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(mergedFile));
        String line1 = br1.readLine(), line2 = br2.readLine();
        while (line1 != null && line2 != null) {
            if (Integer.parseInt(line1.split(" ")[0]) > Integer.parseInt(line2.split(" ")[0])) {
                writer.write(line2 + "\n");
                line2 = br2.readLine();
            } else {
                writer.write(line1 + "\n");
                line1 = br1.readLine();
            }
        }
        if (line1 != null) {
            while (line1 != null) {
                writer.write(line1 + "\n");
                line1 = br1.readLine();
            }
        }
        if (line2 != null) {
            while (line2 != null) {
                writer.write(line2 + "\n");
                line2 = br2.readLine();
            }
        }
        writer.close();
        br1.close();
        br2.close();
        fileStream1.close();
        fileStream2.close();
        return mergedFile;
    }
}
