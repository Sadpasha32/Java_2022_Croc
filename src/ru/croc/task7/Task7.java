package ru.croc.task7;


import java.util.Scanner;

public class Task7 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ChessPosition currentPosition, nextPosition;
        System.out.println("Вводите ходы по одному в строку: ");
        try{
            currentPosition = ChessPosition.parse(sc.nextLine());
            while(sc.hasNextLine()){ // Ввод заканчиваю с помощью Ctrl+D
                nextPosition = ChessPosition.parse(sc.nextLine());
                currentPosition.checkNextMoveOfKnight(nextPosition);
            }
        } catch (IllegalMoveException | IllegalPositionException ex){
            System.out.println(ex.getMessage());
        }

    }
}
