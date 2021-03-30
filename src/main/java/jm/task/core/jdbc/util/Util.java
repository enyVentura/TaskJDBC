package jm.task.core.jdbc.util;
/**
 * Connection config
 * @author Eugene Kashitsyn
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "3157860";

    Connection connection = null;
    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение установлено");
            }
        } catch (SQLException ex) {
            System.err.println("Не удалось загрузить драйвер!");
            ex.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
