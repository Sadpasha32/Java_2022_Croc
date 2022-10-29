package ru.croc.task7;

public class ChessPosition {
    private int x;
    private int y;

    private static final char[] POSITION_LETTERS = new char[]{'a','b','c','d','e','f','g','h'};

    public ChessPosition(int x, int y) throws IllegalPositionException {
        this.x = x;
        this.y = y;
        if(x > 8 || y > 8){
            throw new IllegalPositionException();
        }
    }
    public void move(ChessPosition chessPosition) throws IllegalMoveException {
        if(Math.abs(chessPosition.getX() - x) == 2 && Math.abs(chessPosition.getY() - y) == 1 ||
                Math.abs(chessPosition.getY() - y) == 2 && Math.abs(chessPosition.getX() - x) == 1){
            x = chessPosition.getX();
            y = chessPosition.getY();
        } else{
            throw new IllegalMoveException(this,chessPosition);
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return POSITION_LETTERS[x] + String.format("%d",y);
    }
}
