package ru.croc.task17;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

public class Task17 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/" + "E:\\JavaProjects\\Java_2022_Croc\\Database\\my", "sa", "");
        System.out.println("Database created");
        createTables(con);
        ArrayList<String[]> allData = new ArrayList<>();
        Set<Product> products = new HashSet<>();
        ArrayList<Client> clients = new ArrayList<>();
        parseDataFromCVS(args[0], allData, products, clients);
        clients.sort((o1, o2) -> (int) (o1.id - o2.id));
        for (Client client1 : clients) {
            addClient(client1, con);
        }
        for (Product product1 : products) {
            addProduct(product1, con);
        }
        for (String[] data : allData) {
            addOrder(Integer.parseInt(data[0]), data[2], con);
        }
        testRelationships(con);
        con.close();
    }

    public static void createTables(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            String sqlQuery = "DROP TABLE Orders IF EXISTS;DROP TABLE Products IF EXISTS;DROP TABLE Clients IF EXISTS;";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "CREATE TABLE Clients (idClient long PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20));";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "CREATE TABLE Products (art VARCHAR(20) PRIMARY KEY, nameOfProduct VARCHAR(20), price int);";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "CREATE TABLE Orders (idOrder long PRIMARY KEY AUTO_INCREMENT, idClient long , artProduct VARCHAR(20)," +
                    " FOREIGN KEY(idClient) REFERENCES Clients(idClient) ON DELETE CASCADE,  " +
                    "FOREIGN KEY(artProduct) REFERENCES Products(art) ON DELETE CASCADE);";
            stmt.executeUpdate(sqlQuery);
            System.out.println("Tables created");
        }
    }

    public static void parseDataFromCVS(String filepath, ArrayList<String[]> allData, Set<Product> products, ArrayList<Client> clients) throws FileNotFoundException {
        File cvsFile = new File(filepath);
        Scanner sc = new Scanner(cvsFile);
        Client client;
        Product product;
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            str = str.replaceAll(" ", "");
            String[] data = str.split(",");
            client = new Client(Integer.parseInt(data[0]), data[1]);
            product = new Product(data[2], data[3], Integer.parseInt(data[4]));
            if (!clients.contains(client)) clients.add(new Client(Integer.parseInt(data[0]), data[1]));
            products.add(product);
            allData.add(data);
        }
    }

    public static void addClient(Client client1, Connection con) throws SQLException {
        String sqlClient = "INSERT INTO Clients(name) VALUES(?);";
        PreparedStatement stmtClient = con.prepareStatement(sqlClient);
        stmtClient.setString(1, client1.name);
        stmtClient.executeUpdate();
        stmtClient.close();
    }

    public static void addProduct(Product product1, Connection con) throws SQLException {
        String sqlProduct = "INSERT INTO Products(art,nameOfProduct,price) VALUES(?,?,?);";
        PreparedStatement stmtProduct = con.prepareStatement(sqlProduct);
        stmtProduct.setString(1, product1.articul);
        stmtProduct.setString(2, product1.name);
        stmtProduct.setInt(3, product1.cost);
        stmtProduct.executeUpdate();
        stmtProduct.close();
    }

    public static void addOrder(int idClient, String articulOfProduct, Connection con) throws SQLException {
        String sqlOrder = "INSERT INTO Orders(idClient,artProduct) VALUES(?,?);";
        PreparedStatement stmtOrder = con.prepareStatement(sqlOrder);
        stmtOrder.setInt(1, idClient);
        stmtOrder.setString(2, articulOfProduct);
        stmtOrder.executeUpdate();
        stmtOrder.close();
    }

    public static void testRelationships(Connection con) {
        try (Statement statement = con.createStatement()) {
            boolean hasResult = statement.execute("SELECT Clients.idClient,Clients.name,Products.art,Products.nameOfProduct,Products.price"
                    + " FROM Orders JOIN Clients ON Orders.idClient = Clients.idClient"
                    + " JOIN Products ON Orders.artProduct = Products.art;");
            if (hasResult) {
                try (ResultSet result = statement.getResultSet()) {
                    while (result.next()) {
                        System.out.println(result.getInt(1) + " " + result.getString(2) + " " +
                                result.getString(3) + " " + result.getString(4) + " " + result.getInt(5));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
