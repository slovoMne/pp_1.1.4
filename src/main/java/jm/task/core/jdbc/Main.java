package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Irina ", "Somova", (byte) 38);
        userDaoJDBC.saveUser("Marina ", "Mamonova", (byte) 32);
        userDaoJDBC.saveUser("Alex", "Smith", (byte) 22);
        userDaoJDBC.saveUser("Steve", "Doom", (byte) 55);
        userDaoJDBC.dropUsersTable();
        userDaoJDBC.removeUserById(1);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.getAllUsers();
        System.out.println(userDaoJDBC.getAllUsers());
    }
}
