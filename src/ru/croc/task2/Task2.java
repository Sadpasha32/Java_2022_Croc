package ru.croc.task2;

public class Task2 {
    public static Res changeView(Long value){
        double resVal = value;
        int numberOfUnit = 0;
        while(resVal/1024 >= 1){
            numberOfUnit++;
            resVal = resVal/1024.0;
        }
        return new Res(resVal,numberOfUnit);
    }
    public static void main(String[] args){
        String[] units = {"B","KB","MB","GB","TB"};
        Res result = changeView(Long.parseLong(args[0]));
        if(result.getNumberOfUnit() > 4){
            System.out.println("Слишком большое значение байт!");
        } else System.out.println(String.format("%.1f",result.getResVal())+" "+ units[result.getNumberOfUnit()]);
    }
}
class Res{
    private double resVal;
    private int numberOfUnit;
    Res(double resVal, int numberOfUnit){
        this.resVal = resVal;
        this.numberOfUnit = numberOfUnit;
    }
    public double getResVal() {
        return resVal;
    }

    public int getNumberOfUnit() {
        return numberOfUnit;
    }

    public void setResVal(double resVal) {
        this.resVal = resVal;
    }

    public void setNumberOfUnit(int numberOfUnit) {
        this.numberOfUnit = numberOfUnit;
    }
}
