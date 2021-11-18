package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl Service = new UserDaoJDBCImpl();

    public void createUsersTable() {
        Service.createUsersTable();
    }

    public void dropUsersTable() {
        Service.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        Service.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        Service.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return Service.getAllUsers();
    }

    public void cleanUsersTable() {
        Service.cleanUsersTable();
    }
}
