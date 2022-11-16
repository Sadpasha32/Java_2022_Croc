package ru.croc.task11;

import java.security.Timestamp;
import java.time.LocalDateTime;

public class LotClass{
    double currentPrice;
    private String nameOfOwner;
    private LocalDateTime time;

    private static final Object lock = new Object();


    public LotClass(double currentPrice, String nameOfOwner, LocalDateTime time) {
        this.currentPrice = currentPrice;
        this.nameOfOwner = nameOfOwner;
        this.time = time;
    }
    public void  bet(int newPrice, String nameOfNewOwnre, LocalDateTime currentTime){
        synchronized (lock){
            if (newPrice > currentPrice && currentTime.isBefore(time)){
                currentPrice = newPrice;
                nameOfOwner = nameOfNewOwnre;
            }
        }
    }
    public String getWinner(){
        return nameOfOwner;
    }
}
