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

    public void createUsersTable() throws SQLException {
        String createTable = "CREATE TABLE User (id INT , name VARCHAR(45), lastName VARCHAR(45), age INT(3))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public void dropUsersTable() throws SQLException {
        String dropTable="DROP TABLE User";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropTable);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String addUser="INSERT User (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(addUser);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public void removeUserById(long id) throws SQLException {
        String removeWhereId="DELETE From User WHERE id=id";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(removeWhereId);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public List<User> getAllUsers() throws SQLException {
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
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        String clean="TRUNCATE TABLE User";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clean);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
    }
}
