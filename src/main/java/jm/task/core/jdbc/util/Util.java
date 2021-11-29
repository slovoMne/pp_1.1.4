package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

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
                settings.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydbtest?useSSL=false");
                settings.setProperty("hibernate.connection.username", "root");
                settings.setProperty("hibernate.connection.password", "root");
                settings.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
                settings.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.setProperty("hibernate.current_session_context_class", "thread");
                settings.setProperty("show_sql", "true");
                settings.setProperty("hibernate.hbm2ddl.auto", "update");

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
}
