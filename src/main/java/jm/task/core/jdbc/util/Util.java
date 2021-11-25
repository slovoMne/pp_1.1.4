package jm.task.core.jdbc.util;

//import jdk.internal.access.JavaIOFileDescriptorAccess;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydbtest";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Well done!! Connection successful! Hibernate is working)");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Sorry, сonnection failed. Hibernate is not working(");
            }
        }
        return sessionFactory;
    }


    public static SessionFactory getSessionFactory() {
    }
}
