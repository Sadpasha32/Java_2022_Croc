package ru.croc.task1;

import java.util.Scanner;

public class Task1 {
    static class Point {
        double x;
        double y;
    }
    public static void writePoint(Point p, Scanner sc, int number){ // Метод, для ввода координат
        System.out.print("Введите координату х вершины №"+number+": ");
        p.x = sc.nextInt();
        System.out.print("Введите координату y вершины №"+number+": ");
        p.y = sc.nextInt();
    }
    public static void calculateArea(Point p1, Point p2, Point p3){ // Вычисление площади
        double line1 = Math.sqrt(Math.pow(p1.x-p2.x,2) + Math.pow(p1.y-p2.y,2));
        double line2 = Math.sqrt(Math.pow(p1.x-p3.x,2) + Math.pow(p1.y-p3.y,2));
        double line3 = Math.sqrt(Math.pow(p3.x-p2.x,2) + Math.pow(p3.y-p2.y,2));
        if(line1+line2 <= line3 || line2+line3 <= line1 || line3+line1 <= line2){ // Проверка на условие треугольника
            System.out.println("Введены неверные значения точек!");
            return;
        }
        double p = (line1+line2+line3)/2;
        double S = Math.sqrt(p*(p-line1)*(p-line2)*(p-line3));
        System.out.printf("Площадь треугольника: " + String.format("%.1f",S));
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Point point1 = new Point(), point2 = new Point(), point3 = new Point();
        writePoint(point1,sc,1);
        writePoint(point2,sc,2);
        writePoint(point3,sc,3);
        calculateArea(point1,point2,point3);
    }
}
