package ru.croc.task15;

public class OneDepartment {
    String codeOfDepartment;
    int numberOfHours;

    public OneDepartment(String codeOfDepartment, int numberOfHours) {
        this.codeOfDepartment = codeOfDepartment;
        this.numberOfHours = numberOfHours;
    }

    @Override
    public String toString() {
        return codeOfDepartment + ":" + numberOfHours;
    }
}
