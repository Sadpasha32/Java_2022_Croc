package ru.croc.task15;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Task15 {
    public static void main(String[] args) throws IOException {
        Departments departments = new Departments();
        departments.fillTree();
        int resHours = dfs(departments.treeOfDepartments, departments.rootDepartment, departments.rootTime);
        System.out.println(resHours);
    }

    public static int dfs(HashMap<String, List<OneDepartment>> treeOfDepartments, String rootDepartment, int val) {
        if (!treeOfDepartments.containsKey(rootDepartment) || treeOfDepartments.get(rootDepartment).size() == 0) {
            return val;
        }
        int resHours = 0;
        for (OneDepartment department : treeOfDepartments.get(rootDepartment)) {
            resHours = Math.max(resHours, dfs(treeOfDepartments, department.codeOfDepartment, val + department.numberOfHours));
        }
        return resHours;
    }
}
