package ru.croc.task13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CinemaClass {
    private final HashMap<Integer, String> allFilms = new HashMap<>();
    private final HashMap<Integer, Set<Integer>> allUsersHistory = new HashMap<>();
    private final HashMap<Integer, Integer> filmsFrequency = new HashMap<>();

    public void fillFilms(String filePath) throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] splitLine;
            while ((line = r.readLine()) != null) {
                splitLine = line.split(",");
                if (splitLine.length == 2) allFilms.put(Integer.parseInt(splitLine[0]), splitLine[1]);
            }
        }
    }

    public void fillUsersHistory(String filePath) throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(filePath))) {
            int i = 0;
            String line;
            while ((line = r.readLine()) != null) {
                allUsersHistory.put(i, new HashSet<>());
                String[] splitLine = line.split(",");
                for (String s : splitLine) {
                    int y = Integer.parseInt(s);
                    if (filmsFrequency.containsKey(y)) filmsFrequency.put(y, filmsFrequency.get(y) + 1);
                    else filmsFrequency.put(y, 1);
                    allUsersHistory.get(i).add(y);
                }
                i++;
            }
        }
    }

    public String recommendationAlgorithm(String films) {
        Set<Integer> watchedFilms = new HashSet<>();
        for (String s : films.split(",")) {
            watchedFilms.add(Integer.parseInt(s));
        }
        ArrayList<Integer> chosenUsers = new ArrayList<>();
        int countOfSameFilms;
        for (Integer user : allUsersHistory.keySet()) {
            countOfSameFilms = 0;
            for (Integer film : watchedFilms) {
                if (allUsersHistory.get(user).contains(film)) {
                    countOfSameFilms++;
                    if (countOfSameFilms >= (watchedFilms.size() / 2)) {
                        chosenUsers.add(user);
                        break;
                    }
                }
            }
        }
        int maxFreq = -1;
        int numberOfChosenFilm = -1;
        for (Integer user : chosenUsers) {
            for (Integer film : allUsersHistory.get(user)) {
                if (!watchedFilms.contains(film) && filmsFrequency.get(film) > maxFreq) {
                    numberOfChosenFilm = film;
                    maxFreq = filmsFrequency.get(film);
                }
            }
        }
        if(numberOfChosenFilm != -1) return allFilms.get(numberOfChosenFilm);
        else return "Нет подходящей рекомендации для вас";
    }

    public HashMap<Integer, String> getAllFilms() {
        return allFilms;
    }

    public HashMap<Integer, Set<Integer>> getAllUsersHistory() {
        return allUsersHistory;
    }
}
