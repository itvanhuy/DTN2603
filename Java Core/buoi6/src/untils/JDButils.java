package untils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDButils {
    private static final String URL = "jdbc:mysql://localhost:3306/dtn2603";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Ket noi database thanh cong!");
            } catch (ClassNotFoundException e) {
                System.err.println("Khong tim thay MySQL Driver: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Loi ket noi database: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Da dong ket noi database.");
            } catch (SQLException e) {
                System.err.println("Loi dong ket noi: " + e.getMessage());
            }
        }
    }
}