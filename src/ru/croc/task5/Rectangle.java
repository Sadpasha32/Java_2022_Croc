package ru.croc.task5;

class Rectangle extends Figure{
    int X0,X1;
    int Y0,Y1;

    @Override
    public String toString() {
        return "R ("+X0+", "+Y0+"), ("+X1+", "+Y1+")";
    }
    public Rectangle(int x0, int y0, int x1, int y1) throws Exception {
        if(x1 < x0 || y1 < y0){
            throw new Exception("Неверные данные координат прямоугольника!");
        }
        X0 = x0;
        X1 = x1;
        Y0 = y0;
        Y1 = y1;
    }
}
