package ru.croc.task16;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    long time;
    Date date;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    String message;

    public Log(long time, String message) {
        this.time = time;
        this.message = message;
    }

    @Override
    public String toString() {
        date = new Date(time);
        return format.format(date) + " " + message;
    }

}
