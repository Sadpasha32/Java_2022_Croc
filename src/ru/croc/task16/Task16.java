package ru.croc.task16;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task16 {
    public static void main(String[] args) {
        try (Stream<Path> paths = Files.walk(Paths.get("logs/"))) {
            List<String> logFiles = paths.map(Path::toString)
                    .filter(x -> x.endsWith(".log") || x.endsWith(".trace"))
                    .collect(Collectors.toList());
            ArrayList<Log> logs = getAndSortLogs(logFiles);
            for (Log log : logs) {
                System.out.println(log);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Log> getAndSortLogs(List<String> logFiles) {
        ArrayList<Log> logs = new ArrayList<>();
        for (String filePath : logFiles) {
            try(FileInputStream fstream = new FileInputStream(filePath)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String line;
                String[] splitLine;
                while ((line = br.readLine()) != null) {
                    splitLine = line.split(" ");
                    logs.add(new Log(Long.parseLong(splitLine[0]), splitLine[1]));
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        logs.sort((o1, o2) -> (int) (o1.time - o2.time));
        return logs;
    }

}
