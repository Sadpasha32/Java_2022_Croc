package ru.croc.task10;

import java.util.concurrent.ExecutionException;

public class Solution {
    private static final long LAST_STRING = (long)((1) * Math.pow(26, 7)-1);
    public static final long STRING_POWER = (long) Math.pow(26, 6);
    public static String calculatePassword(int threadsNumber, String hash ) throws ExecutionException, InterruptedException {
        ThreadBruteForcePassword.workStatus.markInCompleted();
        Thread[] pool = new Thread[threadsNumber];
        System.out.println("Working...");
        long start = 0;
        if (threadsNumber > 26)
            threadsNumber = 26;
        for(int i = 0; i < threadsNumber; i++){
            if (i != threadsNumber - 1)
                pool[i] = new Thread(new ThreadBruteForcePassword(start*STRING_POWER, (start + 26 / threadsNumber) * STRING_POWER - 1, hash));
                // Тут я задаю определённые диапазоны для перебора в поток. Например, если nThreads = 2, то первый поток будет передирать
                // от aaaaaa до lzzzzzz, а второй от maaaaaa до zzzzzzz
            else
                pool[i] = new Thread(new ThreadBruteForcePassword(start*STRING_POWER, LAST_STRING, hash));
            start = start + 26 / threadsNumber;
            pool[i].start();
        }
        for(int i = 0; i < threadsNumber; i++){
            pool[i].join();
        }
        return ThreadBruteForcePassword.foundedPassword;
    }

}