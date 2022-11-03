package ru.croc.task6;


class Annotation{
    Figure figure;
    String name;

    @Override
    public String toString() {
        return figure.toString()+ ": " + name;
    }

    public Annotation(Figure figure, String name) {
        this.figure = figure;
        this.name = name;
    }
    public Annotation(){}

    public Figure getFigure() {
        return figure;
    }

    public String getName() {
        return name;
    }
}