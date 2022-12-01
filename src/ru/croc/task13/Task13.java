package ru.croc.task13;

import java.io.IOException;
import java.util.Scanner;

public class Task13 {
    public static void main(String[] args) throws IOException {
        CinemaClass cinemaClass = new CinemaClass();
        cinemaClass.fillFilms("film.txt");
        cinemaClass.fillUsersHistory("users_history.txt");
        System.out.println("Введите ваши просмотренные фильмы в таком формате \"фильм1,фильм2,...\":");
        Scanner sc = new Scanner(System.in);
        System.out.println("Рекомендуем посмотреть вам фильм: " + cinemaClass.recommendationAlgorithm(sc.nextLine()));
    }
}
