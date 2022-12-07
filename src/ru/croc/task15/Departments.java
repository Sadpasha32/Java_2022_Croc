package ru.croc.task15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Departments {
    String rootDepartment = null;
    int rootTime = 0;
    HashMap<String, List<OneDepartment>> treeOfDepartments = new HashMap<>();

    public void fillTree() throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("info.txt"));
        String line;
        while ((line = r.readLine()) != null) {
            String[] stringLine = line.split(",");
            if (Objects.equals(stringLine[1], "-")) {
                rootDepartment = stringLine[0];
                rootTime = Integer.parseInt(stringLine[2]);
                treeOfDepartments.put(stringLine[0], new ArrayList<>());
            } else {
                if (!treeOfDepartments.containsKey(stringLine[1])) {
                    treeOfDepartments.put(stringLine[1], new ArrayList<>());
                }
                treeOfDepartments.get(stringLine[1]).add(new OneDepartment(stringLine[0], Integer.parseInt(stringLine[2])));
            }
        }
        r.close();
    }
}
