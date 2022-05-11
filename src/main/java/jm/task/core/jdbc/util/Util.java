package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/sqlbase";
    private static final String PASSWORD = "root";
    private static final String USER = "root";
    private static Connection connection;

    public static Connection getConnection() {
        try {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        } catch (  SQLException e) {
            e.printStackTrace();
            System.out.println("connect error");
        }
        return connection;
    }
}
