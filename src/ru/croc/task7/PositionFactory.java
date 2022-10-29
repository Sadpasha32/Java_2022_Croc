package ru.croc.task7;

public class PositionFactory {
    static ChessPosition parse(String position) throws IllegalPositionException {
        if(position.length() > 2) throw new IllegalPositionException();
        int x = position.charAt(0) -'a';
        int y = Integer.parseInt(position.substring(1,2));
        return new ChessPosition(x,y);
    }

}
