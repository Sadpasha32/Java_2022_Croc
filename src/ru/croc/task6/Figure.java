package ru.croc.task6;

public abstract class Figure implements Movable{
    private int x;
    private int y;
    @Override
    abstract public String toString();
    abstract public boolean containsPoint(int x,int y);
}
