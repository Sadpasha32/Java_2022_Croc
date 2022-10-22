package ru.croc.task3;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args){
        int[] v = new int[args.length];
        for(int i = 0; i < v.length; i++){
            v[i] = Integer.parseInt(args[i]);
        }
        solution(v);
        System.out.println(Arrays.toString(v));
    }
    public static void solution(int[] v){
        ValueAndIndex minValue = new ValueAndIndex(), maxValue = new ValueAndIndex();
        minValue.value = v[0];
        minValue.index = 0;
        maxValue.value = v[0];
        maxValue.index = 0;
        for (int i = 1; i < v.length; i++) {
            if (v[i] < minValue.value){
                minValue.value = v[i];
                minValue.index = i;
            }
            if (v[i] > maxValue.value){
                maxValue.value = v[i];
                maxValue.index = i;
            }
        }
        int x = v[0];
        v[minValue.index] = x;
        if(maxValue.index == 0) maxValue.index = minValue.index;
        v[0] = minValue.value;
        x = v[v.length-1];
        v[maxValue.index] = x;
        v[v.length-1] = maxValue.value;
    }
}
class ValueAndIndex{ // Класс, чтобы удобно хранить индекс и значение
    public int value;
    public int index;
}
