package ru.croc.task7;

public class IllegalPositionException extends Exception{
    @Override
    public String getMessage() {
        return "Неверная позиция!";
    }
}
