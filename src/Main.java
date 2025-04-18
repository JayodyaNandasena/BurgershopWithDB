import db.DBConnection;
import view.MainMenu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Connected to MySQL!");
        } else {
            System.out.println("Failed to connect.");
        }

        new MainMenu().setVisible(true);
    }
}