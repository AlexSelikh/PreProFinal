package jm.task.core.jdbc.util;





import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/mydbpp";
    private final String USERNAME = "root";
    private final String PASS = "root";
    private Connection connection;
    private static SessionFactory sessionFactory;

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException e) {
            System.out.println("Проблемы с соединением");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            try {
                Properties prop = new Properties();
                prop.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
                prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydbpp");
                prop.setProperty("hibernate.connection.username", "root");
                prop.setProperty("hibernate.connection.password", "root");
                prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                prop.setProperty("hibernate.show_sql", "true");
                prop.setProperty("hibernate.format_sql", "true");



                sessionFactory = new Configuration()
                        .addProperties(prop)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();

            } catch (Exception ex) {
                System.out.println("нет соединения");
            }
        }
        return sessionFactory;

    }
    public static Session getSession(){
        Session session=Util.getSessionFactory().openSession();
        return session;
    }

}
