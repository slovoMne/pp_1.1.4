package jm.task.core.jdbc.util;

//import jdk.internal.access.JavaIOFileDescriptorAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydbtest";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            if (!connection.isClosed()) {
                System.out.println("Молодец, соединение с БД успешно установлено");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Ошибка, не удалось выполнить соединение с БД");
            ex.printStackTrace();

        }
        return DriverManager.getConnection(url, username, password);
    }
}
