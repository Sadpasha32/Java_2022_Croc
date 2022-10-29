package ru.croc.task7;


import java.util.Scanner;

public class Task7 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ChessPosition currentPosition, nextPosition;
        System.out.println("Вводите ходы по одному в строку: ");
        try{
            currentPosition = PositionFactory.parse(sc.nextLine());
            while(sc.hasNextLine()){ // Ввод заканчиваю с помощью Ctrl+D
                nextPosition = PositionFactory.parse(sc.nextLine());
                currentPosition.move(nextPosition);
            }
        } catch (IllegalMoveException | IllegalPositionException ex){
            System.out.println(ex.getMessage());
        }

    }
}
