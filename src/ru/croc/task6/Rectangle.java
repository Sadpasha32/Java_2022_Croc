package ru.croc.task6;

public class Rectangle extends Figure {
    private int X0,X1;
    private int Y0,Y1;

    public Rectangle(int x0, int y0, int x1, int y1) {
        X0 = x0;
        X1 = x1;
        Y0 = y0;
        Y1 = y1;
    }

    @Override
    public String toString() {
        return "R ("+X0+", "+Y0+"), ("+X1+", "+Y1+")";
    }

    @Override
    public boolean containsPoint(int x,int y) {
        return x >= X0 && y >= Y0 && x <= X1 && y <= Y1;
    }

    @Override
    public void move(int dx, int dy) {
        X0 += dx;
        Y0 += dy;
        X1 += dx;
        Y1 += dy;
    }
}
