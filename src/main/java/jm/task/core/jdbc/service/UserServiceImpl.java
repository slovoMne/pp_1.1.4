package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private long id;
    private String name;
    private String lastName;
    private byte age;

    UserDao userDao = new UserDaoJDBCImpl();

    public void createUsersTable() throws Exception {
        userDao.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        this.id = id;
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        userDao.cleanUsersTable();
    }
}
