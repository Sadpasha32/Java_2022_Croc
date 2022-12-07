package ru.croc.task18;

import java.sql.*;
import java.util.List;

public class DAO {
    Connection con;

    public DAO(Connection con) {
        this.con = con;
    }

    Product findProduct(String productCode) {
        Product resProduct = new Product();
        try (Statement stmt = con.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT nameOfProduct,price FROM Products WHERE art = '" + productCode + "';");
            if (resultSet.next()) {
                resProduct.name = resultSet.getString(1);
                resProduct.cost = resultSet.getInt(2);
                resProduct.articul = productCode;
                return resProduct;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Product createProduct(Product product) throws SQLException {
        if (findProduct(product.articul) == null) {
            String sqlProduct = "INSERT INTO Products(art,nameOfProduct,price) VALUES(?,?,?);";
            PreparedStatement stmtProduct = con.prepareStatement(sqlProduct);
            stmtProduct.setString(1, product.articul);
            stmtProduct.setString(2, product.name);
            stmtProduct.setInt(3, product.cost);
            stmtProduct.executeUpdate();
            stmtProduct.close();
            return product;
        } else {
            throw new RuntimeException();
        }
    }

    Product updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Products SET nameOfProduct ='" + product.name + "', price =" + product.cost +
                " WHERE art = '" + product.articul+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        return product;
    }

    void deleteProduct(String productCode) throws SQLException {
        String sql = "DELETE FROM Products WHERE art = " + productCode;
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    Order createOrder(String userLogin, List<Product> products) throws SQLException {
        String sqlOrder;
        PreparedStatement stmtOrder;
        for (Product product : products) {
            createProduct(product);
            sqlOrder = "INSERT INTO Orders(idClient,artProduct) VALUES((SELECT idClient FROM Clients WHERE name = ?),?);";
            stmtOrder = con.prepareStatement(sqlOrder);
            stmtOrder.setString(1, userLogin);
            stmtOrder.setString(2, product.articul);
            stmtOrder.executeUpdate();
            stmtOrder.close();
        }
        return new Order(userLogin, products);
    }
}
