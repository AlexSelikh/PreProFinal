package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL="jdbc:mysql://localhost:3306/mydbpp";
    private final String USERNAME ="root";
    private final String PASS="root";

    private Connection connection;

    public Util(){
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASS);
        } catch (SQLException e) {
            System.out.println("Проблемы с соединением");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
