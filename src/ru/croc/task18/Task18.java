package ru.croc.task18;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

public class Task18 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/" + "E:\\JavaProjects\\Java_2022_Croc\\Database\\my", "sa", "");
        System.out.println("Database created");
        DAO dao = new DAO(con);
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
            dao.createProduct(product1);
        }
        for (String[] data : allData) {
            addOrder(Integer.parseInt(data[0]), data[2], con);
        }
        testRelationships(con);
        System.out.println("\nCheck find function:");
        System.out.println(dao.findProduct("Т1"));
        Product product = new Product(2, "T228", "Ноутбук", 6800);
        System.out.println("\nCheck update function:");
        dao.updateProduct(product);
        testRelationships(con);
        dao.deleteProduct("'T228'");
        System.out.println("\nCheck delete function:");
        testRelationships(con);
        System.out.println("\nCheck create order function:");
        dao.createOrder("olga", Arrays.asList(new Product("Т32","TV",30000)));
        testRelationships(con);
        con.close();
    }

    public static void createTables(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            String sqlQuery = "DROP TABLE Orders IF EXISTS;DROP TABLE Products IF EXISTS;DROP TABLE Clients IF EXISTS;";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "CREATE TABLE Clients (idClient long PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20));";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "CREATE TABLE Products (idProduct long PRIMARY KEY AUTO_INCREMENT, art VARCHAR(20), nameOfProduct VARCHAR(20), price int);";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "CREATE TABLE Orders (idOrder long PRIMARY KEY AUTO_INCREMENT, idClient long , idProduct long," +
                    " FOREIGN KEY(idClient) REFERENCES Clients(idClient) ON DELETE CASCADE,  " +
                    "FOREIGN KEY(idProduct) REFERENCES Products(idProduct) ON DELETE CASCADE);";
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

    public static void addOrder(int idClient, String articulOfProduct, Connection con) throws SQLException {
        String sqlOrder = "INSERT INTO Orders(idClient,idProduct) VALUES(?,(SELECT idProduct FROM PRODUCTS WHERE art = ?));";
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
                    + " JOIN Products ON Orders.idProduct = Products.idProduct;");
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