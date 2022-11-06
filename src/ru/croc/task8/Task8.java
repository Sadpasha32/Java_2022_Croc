package ru.croc.task8;

import java.io.*;

public class Task8 {
    private static final String SIGNS_SET = ",./?!\\@#$%^&*()_+|'\"`~№;%:;";
    public static void main(String[] args) {
        try(BufferedReader r = new BufferedReader(new FileReader(args[0]))){
            String line;
            int numberOfWords = 0;
            while ((line = r.readLine()) != null){
                numberOfWords += counterOfWordsInLine(line);
            }
            System.out.println(numberOfWords);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static int counterOfWordsInLine(String s){
        int numberOfWords = 0;
        int leftPoint = 0,rightPoint;
        while(leftPoint < s.length()){
            if(s.charAt(leftPoint) != ' ' && !SIGNS_SET.contains(s.substring(leftPoint,leftPoint+1)) && s.charAt(leftPoint) != '\n'){
                rightPoint = leftPoint;
                while(rightPoint < s.length() && s.charAt(rightPoint) != ' ' && s.charAt(rightPoint) != '\n'){
                    rightPoint++;
                }
                numberOfWords++;
                rightPoint++;
                leftPoint = rightPoint;
            }else {
                leftPoint++;
            }
        }
        return numberOfWords;
    }
}
