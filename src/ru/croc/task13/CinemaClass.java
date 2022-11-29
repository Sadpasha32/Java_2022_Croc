package ru.croc.task13;


import java.util.*;

public class CinemaClass {
    private final HashMap<Integer, String> allFilms = new HashMap<>();
    private final HashMap<Integer, Set<Integer>> allUsersHistory = new HashMap<>();
    private final HashMap<Integer, Integer> filmsFrequency = new HashMap<>();

    public void fillFilms(Scanner sc){
        System.out.println("Введите фильмы в формате \"номер,название\".\nЗакончите ввод цифрой 0:");
        String line = sc.nextLine();
        String[] splitLine;
        while (!Objects.equals(line, "0")) {
            splitLine = line.split(",");
            if (splitLine.length == 2) allFilms.put(Integer.parseInt(splitLine[0]), splitLine[1]);
            line = sc.nextLine();
        }
    }

    public void fillUsersHistory(Scanner sc){
        System.out.println("Введите историю просмотров пользователей в формате \"номер фильма,номер фильма,...\".\n Закончите ввод цифрой 0:");
        String line = sc.nextLine();
        int i = 0;
        while (!Objects.equals(line, "0")) {
            allUsersHistory.put(i, new HashSet<>());
            String[] splitLine = line.split(",");
            for (String s : splitLine) {
                int y = Integer.parseInt(s);
                if (filmsFrequency.containsKey(y)) filmsFrequency.put(y, filmsFrequency.get(y) + 1);
                else filmsFrequency.put(y, 1);
                allUsersHistory.get(i).add(y);
            }
            i++;
            line = sc.nextLine();
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

    public HashMap<Integer, Integer> getFilmsFrequency() {
        return filmsFrequency;
    }
}
