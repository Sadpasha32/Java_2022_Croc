package ru.croc.task13;

import java.io.IOException;

public class Task13 {
    public static void main(String[] args) throws IOException {
        CinemaClass cinemaClass = new CinemaClass();
        cinemaClass.fillFilms("E:\\JavaProjects\\Java-2022-Сroc\\src\\ru\\croc\\task13\\film.txt");
        cinemaClass.fillUsersHistory("E:\\JavaProjects\\Java-2022-Сroc\\src\\ru\\croc\\task13\\users_history.txt");
        System.out.println(cinemaClass.getAllFilms().toString());
        System.out.println(cinemaClass.getAllUsersHistory().toString());
        System.out.println(cinemaClass.recommendationAlgorithm("2,4"));
    }
}
