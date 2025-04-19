package controller;

import db.DBConnection;
import model.Customer;
import model.Orders;
import model.enums.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private OrderController() {
    }

    // generate order Id
    public static String generateOrderId() throws SQLException {
        String lastId = getLastId();

        if (lastId == null) {
            lastId = "B0001";
        } else {
            int number = Integer.parseInt(lastId.split("B")[1]);
            number++;

            lastId = String.format("B%04d", number);
        }
        return lastId;
    }

    // generate order value
    public static double generateValue(int quantity) {
        final double BURGER_PRICE = 500.00;
        return quantity * BURGER_PRICE;
    }

    // add new order
    public static boolean add(Orders order) throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "INSERT INTO orders (id, status, quantity, value, customerId) VALUES (?, ?, ?, ?, ?)")
        ) {
            stmt.setString(1, order.getId());
            stmt.setString(2, order.getStatus().toString());
            stmt.setInt(3, order.getQuantity());
            stmt.setDouble(4, order.getValue());
            stmt.setString(5, order.getCustomerId());
            stmt.executeUpdate();
            return true;
        }
    }

    // update order
    public static void update(String orderId, OrderStatus status, int quantity, double value) throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "UPDATE orders SET status = ?, quantity = ?, value = ? WHERE id = ?")
        ) {
            stmt.setString(1, status.toString());
            stmt.setInt(2, quantity);
            stmt.setDouble(3, value);
            stmt.setString(4, orderId);
            stmt.executeUpdate();
        }
    }

    // search order by id, join customer
    public static Orders byId(String orderId) throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "SELECT o.id, o.status, o.quantity, o.value, o.customerId, c.name " +
                                "FROM orders o JOIN customer c ON o.customerId = c.id " +
                                "WHERE o.id = ?")
        ) {
            stmt.setString(1, orderId);

            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    // Create the Customer object
                    Customer customer = new Customer(
                            result.getString("customerId"),
                            result.getString("name")
                    );

                    // Create and return the Orders object
                    return new Orders(
                            result.getString("id"),
                            result.getString("customerId"),
                            OrderStatus.valueOf(result.getString("status")),
                            result.getInt("quantity"),
                            result.getDouble("value"),
                            customer
                    );
                }
                return null;
            }
        }
    }

    // search order by customer id
    public static List<Orders> byCustomerId(String customerId) throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "SELECT id, status, quantity, value, customerId FROM orders WHERE customerId = ?")
        ) {
            stmt.setString(1, customerId);

            try (ResultSet result = stmt.executeQuery()) {
                List<Orders> ordersList = new ArrayList<>();

                while (result.next()) {
                    Orders order = new Orders(
                            result.getString("id"),
                            result.getString("customerId"),
                            OrderStatus.valueOf(result.getString("status")),
                            result.getInt("quantity"),
                            result.getDouble("value")
                    );
                    ordersList.add(order);
                }

                return ordersList;
            }
        }
    }

    // get all with customer
    public static List<Orders> all() throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "SELECT o.id, o.status, o.quantity, o.value, o.customerId, c.name " +
                                "FROM orders o JOIN customer c ON o.customerId = c.id ")
        ) {
            try (ResultSet result = stmt.executeQuery()) {
                List<Orders> ordersList = new ArrayList<>();

                while (result.next()) {
                    Customer customer = new Customer(
                            result.getString("customerId"),
                            result.getString("name")
                    );
                    Orders order = new Orders(
                            result.getString("id"),
                            result.getString("customerId"),
                            OrderStatus.valueOf(result.getString("status")),
                            result.getInt("quantity"),
                            result.getDouble("value"),
                            customer
                    );
                    ordersList.add(order);
                }

                return ordersList;
            }
        }
    }

    // get all with customer by status
    public static List<Orders> byStatusWithCustomer(OrderStatus status) throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "SELECT o.id, o.status, o.quantity, o.value, o.customerId, c.name " +
                                "FROM orders o JOIN customer c ON o.customerId = c.id WHERE o.status = ? ")
        ) {
            stmt.setString(1, status.toString());

            try (ResultSet result = stmt.executeQuery()) {
                List<Orders> ordersList = new ArrayList<>();

                while (result.next()) {
                    Customer customer = new Customer(
                            result.getString("customerId"),
                            result.getString("name")
                    );
                    Orders order = new Orders(
                            result.getString("id"),
                            result.getString("customerId"),
                            OrderStatus.valueOf(result.getString("status")),
                            result.getInt("quantity"),
                            result.getDouble("value"),
                            customer
                    );
                    ordersList.add(order);
                }

                return ordersList;
            }
        }
    }

    // get last order id
    private static String getLastId() throws SQLException {
        try (
                Connection dbConnection = DBConnection.getConnection();
                PreparedStatement stmt = dbConnection.prepareStatement(
                        "SELECT id FROM orders ORDER BY id DESC LIMIT 1"
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
}
