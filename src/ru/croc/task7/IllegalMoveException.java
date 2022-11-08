package ru.croc.task7;

public class IllegalMoveException extends Exception{
    private ChessPosition position1;
    private ChessPosition position2;
    public IllegalMoveException(ChessPosition position1, ChessPosition position2){
        this.position1 = position1;
        this.position2 = position2;
    }
    @Override
    public String getMessage() {
        return "конь так не ходит: " + position1 + " -> " + position2;
    }
}
