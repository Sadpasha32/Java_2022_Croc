package ru.croc.task5;

class Annotation{
    Figure figure;
    String name;

    @Override
    public String toString() {
        return figure.toString() + ": "+ name;
    }

    public Annotation(Figure figure, String name) {
        this.figure = figure;
        this.name = name;
    }
}