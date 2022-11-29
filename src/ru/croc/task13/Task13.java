package ru.croc.task13;

import java.io.IOException;
import java.util.Scanner;

public class Task13 {
    public static void main(String[] args) throws IOException {
        CinemaClass cinemaClass = new CinemaClass();
        Scanner sc = new Scanner(System.in);
        cinemaClass.fillFilms(sc);
        cinemaClass.fillUsersHistory(sc);
        System.out.println(cinemaClass.getAllFilms().toString());
        System.out.println(cinemaClass.getAllUsersHistory().toString());
        System.out.println(cinemaClass.getFilmsFrequency().toString());
        System.out.println(cinemaClass.recommendationAlgorithm("2,4"));
    }
}
