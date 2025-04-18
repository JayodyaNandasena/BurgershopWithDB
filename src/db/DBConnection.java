package db;

import exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/burgershop_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static Connection connection;

    private DBConnection() {
    }

    public static Connection getConnection(){
        try{
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseConnectionException("Failed to get DB connection", e);
        }
    }
}
