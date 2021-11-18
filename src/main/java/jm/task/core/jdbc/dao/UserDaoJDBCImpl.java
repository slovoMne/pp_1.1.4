package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getConnection();
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE User (id INT , name VARCHAR(45), lastName VARCHAR(45), age INT(3))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTable="DROP TABLE User";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String addUser="INSERT User (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(addUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeWhereId="DELETE From User WHERE id=id";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(removeWhereId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from User");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String clean="TRUNCATE TABLE User";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
