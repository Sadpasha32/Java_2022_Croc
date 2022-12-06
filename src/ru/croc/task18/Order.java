package ru.croc.task18;

import java.util.List;

public class Order {
    String userLogin;
    List<Product> products;

    public Order(String userLogin, List<Product> products) {
        this.userLogin = userLogin;
        this.products = products;
    }
}
