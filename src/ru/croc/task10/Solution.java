package ru.croc.task10;

import java.util.concurrent.ExecutionException;

public class Solution {
    public static String calculatePassword(int threadsNumber, String hash ) throws ExecutionException, InterruptedException {
        ThreadBruteForcePassword.workStatus.markInCompleted();
        Thread[] pool = new Thread[threadsNumber];
        System.out.println("Working...");
        long start = 0;
        if (threadsNumber > 26)
            threadsNumber = 26; // Есть идея, как можно лучше оптимизировать, но не успеваю реализовать, так как сложновато :(
        for(int i = 0; i < threadsNumber; i++){
            if (i != threadsNumber - 1)
                pool[i] = new Thread(new ThreadBruteForcePassword((long) (start * Math.pow(26, 6)), (long) ((start + 26 / threadsNumber) * Math.pow(26, 6) - 1), hash));
                // Тут я задаю определённые диапазоны для перебора в поток. Например, если nThreads = 2, то первый поток будет передирать
                // от aaaaaa до lzzzzzz, а второй от maaaaaa до zzzzzzz
            else
                pool[i] = new Thread(new ThreadBruteForcePassword((long) (start * Math.pow(26, 6)), (long)((1) * Math.pow(26, 6)), hash));
            start = start + 26 / threadsNumber;
            pool[i].start();
        }
        for(int i = 0; i < threadsNumber; i++){
            pool[i].join();
        }
        return ThreadBruteForcePassword.foundedPassword;
    }
}
