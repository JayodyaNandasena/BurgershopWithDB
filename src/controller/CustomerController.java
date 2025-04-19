package controller;

import db.DBConnection;
import model.Customer;
import model.CustomerInOrder;
import model.enums.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private CustomerController() {
    }

    // generate customer Id
    public static String generateCustomerId() throws SQLException {
        String lastId = getLastId();

        if (lastId == null) {
            lastId = "C0001";
        } else {
            int number = Integer.parseInt(lastId.split("C")[1]);
            number++;

            lastId = String.format("C%04d", number);
        }
        return lastId;
    }

    // validation Customer Name
    public static boolean validateCustomerName(String name) {
        return !Character.isDigit(name.charAt(0));
    }

    // Add new customer
    public static boolean add(Customer customer) throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO customer (id, name) VALUES (?, ?)")
        ) {
            stmt.setString(1, customer.getId());
            stmt.setString(2, customer.getName());
            stmt.executeUpdate();
            return true;
        }
    }

    // Search customer by ID
    public static String search(String id) throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "SELECT name FROM customer WHERE id = ?"
                )
        ) {
            stmt.setString(1, id);

            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return result.getString("name");
                }
                return null;
            }
        }
    }

    // search best customer
    public static List<CustomerInOrder> bestCustomer() throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "SELECT " +
                                "c.id AS customerId," +
                                "c.name AS customerName," +
                                "SUM(o.value) AS totalOrderValue " +
                                "FROM customer c " +
                                "JOIN orders o ON c.id = o.customerId " +
                                "WHERE o.status = ? " +
                                "GROUP BY c.id, c.name " +
                                "ORDER BY totalOrderValue DESC"
                )
        ) {
            stmt.setString(1, OrderStatus.DELIVERED.toString());

            try (ResultSet result = stmt.executeQuery()) {

                List<CustomerInOrder> customersList = new ArrayList<>();

                while (result.next()) {
                    CustomerInOrder customer = new CustomerInOrder(
                            result.getString("customerId"),
                            result.getString("customerName"),
                            result.getDouble("totalOrderValue")
                    );
                    customersList.add(customer);
                }

                return customersList;
            }
        }
    }

    // get last customer id
    private static String getLastId() throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "SELECT id FROM customer ORDER BY id DESC LIMIT 1"
                )
        ) {
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return result.getString("id");
                }
                return null;
            }
        }
    }

    // get customer name by id
    public static String getName(String id) throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "SELECT name FROM customer WHERE id = ?"
                )
        ) {
            stmt.setString(1, id);

            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return result.getString("name");
                }
                return null;
            }
        }
    }
}
