package ru.croc.task15;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Task15 {
    public static void main(String[] args) throws IOException {
        HashMap<String, List<Node>> treeOfDepartments = new HashMap<>();
        BufferedReader r = new BufferedReader(new FileReader("info.txt"));
        String line;
        String rootDepartment = null;
        int rootTime = 0;
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
                treeOfDepartments.get(stringLine[1]).add(new Node(stringLine[0], Integer.parseInt(stringLine[2])));
            }
        }
        int resHours = dfs(treeOfDepartments, rootDepartment, rootTime);
        System.out.println(resHours);
    }

    public static int dfs(HashMap<String, List<Node>> treeOfDepartments, String rootDepartment, int val) {
        if (!treeOfDepartments.containsKey(rootDepartment) || treeOfDepartments.get(rootDepartment).size() == 0) {
            return val;
        }
        int resHours = 0;
        for (Node node : treeOfDepartments.get(rootDepartment)) {
            resHours = Math.max(resHours, dfs(treeOfDepartments, node.codeOfDepartment, val + node.numberOfHours));
        }
        return resHours;
    }
}
