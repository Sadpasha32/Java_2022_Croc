package ru.croc.task9;

import java.util.LinkedList;
import java.util.Scanner;

public class Task9 {
    public static void main(String[] args) {
        LinkedList<String> allDirectories = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        getDirectories(path, allDirectories);
        String validPath = getValidPath(allDirectories);
        System.out.println(validPath);
    }

    public static void getDirectories(String s, LinkedList<String> allDirectories) {
        int leftPoint = 0, rightPoint = 0;
        while (rightPoint < s.length()) {
            while (rightPoint < s.length() && s.charAt(rightPoint) != '/') {
                rightPoint++;
            }
            if (!s.substring(leftPoint, rightPoint).equals(".")) allDirectories.add(s.substring(leftPoint, rightPoint));
            rightPoint++;
            leftPoint = rightPoint;
        }
    }

    public static String getValidPath(LinkedList<String> allDirectories) {
        StringBuilder validPath = new StringBuilder("");
        int currentIndex = 0;
        while (currentIndex < allDirectories.size()) {
            if (currentIndex != 0 && allDirectories.get(currentIndex).equals("..")) {
                if (currentIndex - 1 >= 0 && !allDirectories.get(currentIndex - 1).equals("..")) {
                    allDirectories.remove(currentIndex);
                    if (!(allDirectories.get(currentIndex - 1).equals("..") && currentIndex - 1 == 0))
                        allDirectories.remove(currentIndex - 1);
                    currentIndex--;
                } else {
                    currentIndex++;
                }
            } else {
                currentIndex++;
            }
        }
        for (int i = 0; i < allDirectories.size(); i++) {
            validPath.append(allDirectories.get(i));
            if (i != allDirectories.size() - 1) validPath.append("/");
        }
        return validPath.toString();
    }
}
