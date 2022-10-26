package ru.croc.task4;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = "";
        while(sc.hasNextLine()){ // Ввод строки завершаю с помощью Ctrl+D
            s += sc.nextLine() + '\n';
        }
        s = removeJavaComments(s);
        System.out.println(s);
    }
    public static String removeJavaComments(String s) {
        int start,end;
        int i = 0;
        while(i < s.length()){
            if(i < s.length()-1 && (s.charAt(i) == '/' && s.charAt(i+1) == '*')){
               s = removeComdComm(s,i);
            } else if (i < s.length()-1 && (s.charAt(i) == '/' && s.charAt(i+1) == '/')){
                s = removeRegComm(s,i);
            } else{
                i++;
            }
        }
        return s;
    }
    public static String removeComdComm(String s,int start){
        int flag = 0; // Данный флаг нужен для того, чтобы проверить случай, когда /* не закрыт
        int end = start+3;
        if(end >= s.length())
            return s;
        while(!(s.charAt(end-1) == '*' && s.charAt(end) == '/')){
            end++;
            if(end == s.length()){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            s = s.replace(s.substring(start,end+1),"");
        }
        return s;
    }
    public static String removeRegComm(String s,int start){
        int flag = 0;
        int end = start+1;
        while(s.charAt(end) != '\n'){
            if(s.charAt(end) == '"'){
                return s;
            }
            end++;
            if(end == s.length()){
                end--;
                break;
            }
        }
        if(end-start == 2){
            s = s.replaceFirst(s.substring(start,end),"");
        } else{
            s = s.replace(s.substring(start,end),"");
        }
        return s;
    }
}
