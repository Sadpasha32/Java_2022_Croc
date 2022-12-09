package ru.croc.task16;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task16 {
    public static void main(String[] args) {
        try (Stream<Path> paths = Files.walk(Paths.get(args[0]))) {
            List<String> logFiles = paths.map(Path::toString)
                    .filter(x -> x.toLowerCase().endsWith(".log") || x.toLowerCase().endsWith(".trace"))
                    .collect(Collectors.toList());
            mergeFiles(logFiles);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void mergeFiles(List<String> logsFiles) throws IOException {
        int numberOfFiles = logsFiles.size();
        ArrayList<FileInputStream> fileInputStreams = new ArrayList<>();
        ArrayList<BufferedReader> bufferedReaders = new ArrayList<>();
        String[] lines = new String[numberOfFiles];
        for (String logFile : logsFiles) {
            fileInputStreams.add(new FileInputStream(logFile));
        }
        for (FileInputStream fileInputStream : fileInputStreams) {
            bufferedReaders.add(new BufferedReader(new InputStreamReader(fileInputStream)));
        }
        long max_value = Long.MAX_VALUE;
        String line2 = Long.toString(max_value);
        int numberOfMinReader = -1, numberOfreaderToRread = -1;
        for (int i = 0; i < numberOfFiles; i++) {
            lines[i] = bufferedReaders.get(i).readLine();
        }
        while (true) {
            for (int i = 0; i < numberOfFiles; i++) {
                if(i == numberOfreaderToRread){
                    lines[i] = bufferedReaders.get(i).readLine();
                }
                if (lines[i] != null && Long.parseLong(lines[i].split(" ")[0]) < Long.parseLong(line2.split(" ")[0])) {
                    line2 = lines[i];
                    numberOfMinReader = i;
                }
            }
            if (Long.parseLong(line2.split(" ")[0]) != max_value) {
                System.out.println(line2);
                line2 = Long.toString(max_value);
                numberOfreaderToRread = numberOfMinReader;
            } else {
                break;
            }
        }
        for (BufferedReader bufferedReader : bufferedReaders) {
            bufferedReader.close();
        }
        for (FileInputStream fileInputStream : fileInputStreams) {
            fileInputStream.close();
        }
    }
}
