package ru.croc.task15;

public class Node {
    String codeOfDepartment;
    int numberOfHours;

    public Node(String codeOfDepartment, int numberOfHours) {
        this.codeOfDepartment = codeOfDepartment;
        this.numberOfHours = numberOfHours;
    }

    @Override
    public String toString() {
        return codeOfDepartment + ":" + numberOfHours;
    }
}
