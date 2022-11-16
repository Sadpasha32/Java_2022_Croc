package ru.croc.task10;

public class WorkStatus {
    private boolean completed = false;
    public boolean isCompleted(){
        return completed;
    }
    public void markCompleted(){
        completed = true;
    }
    public void markInCompleted(){
        completed = false;
    }

}
