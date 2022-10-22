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
        for(int i = 0; i < s.length(); i++){
            if(i >= 1 && (s.substring(i-1,i+1)).equals("/*")){
                int flag = 0; // Данный флаг нужен для того, чтобы проверить случай, когда /* не закрыт
                start = i-1;
                end = i+2;
                while(!(s.charAt(end) == '/' && s.charAt(end-1) == '*')){
                    end++;
                    if(end == s.length()){
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0){
                    if(s.charAt(end+1) == '\n'){
                        end++;
                    }
                    s = s.replace(s.substring(start,end+1),"");
                }
            }
            if(i >= 1 && (s.substring(i-1,i+1)).equals("//")){
                start = i-1;
                end = i+2;
                while(!(s.charAt(end) == '\n')){
                    end++;
                }
                s = s.replace(s.substring(start,end),"");
            }
        }
        return s;
    }
}
