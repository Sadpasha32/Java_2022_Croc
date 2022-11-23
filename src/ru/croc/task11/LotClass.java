package ru.croc.task11;

import java.time.LocalDateTime;

public class LotClass {
    private volatile double currentPrice;
    private volatile String nameOfOwner;
    private final LocalDateTime time;
    private static int numberOfBets;

    public LotClass(double currentPrice, LocalDateTime time) {
        this.currentPrice = currentPrice;
        this.time = time;
    }

    public void bet(int newPrice, String nameOfNewOwner, LocalDateTime currentTime) {
        if(newPrice <= currentPrice){
            return;
        }
        synchronized(this) {
            if (newPrice > currentPrice && currentTime.isBefore(time)) {
                currentPrice = newPrice;
                nameOfOwner = nameOfNewOwner;
                numberOfBets++;
            }
        }
    }

    public String getWinner() {
        if(numberOfBets > 0) return nameOfOwner;
        else return "Пока нет владельца";
    }
}
