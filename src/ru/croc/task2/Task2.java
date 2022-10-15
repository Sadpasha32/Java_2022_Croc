package ru.croc.task2;

public class Task2 {
    public static double[] changeView(Long value){
        double resVal = value;
        double numberOfUnit = 0;
        while(value/1024 != 0){
            numberOfUnit++;
            resVal = value/1024.0;
            value = value/1024;
        }
        return new double[]{resVal,numberOfUnit};// Первое значение в data, это само значение, а второе, это номер единицы измерения
    }
    public static void main(String[] args){
        String[] units = {"B","KB","MB","GB","TB"};
        double[] data = changeView(Long.parseLong(args[0]));
        if(data[1] > 4){
            System.out.println("Слишком большое значение байт!");
        } else System.out.println(String.format("%.1f",data[0])+" "+ units[(int)data[1]]);
    }
}
