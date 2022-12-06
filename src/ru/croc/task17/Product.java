package ru.croc.task17;

import java.util.Objects;

public class Product {
    static public int counter;
    int id;
    String articul;
    String name;
    int cost;

    public Product(String articul, String name, int cost) {
        this.articul = articul;
        this.name = name;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(articul, product.articul);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articul);
    }

    @Override
    public String toString() {
        return "Product{" +
                "articul='" + articul + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
