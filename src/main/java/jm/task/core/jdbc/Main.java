package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws Exception {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Irina ", "Somova", (byte) 38);
        userService.saveUser("Marina ", "Mamonova", (byte) 32);
        userService.saveUser("Alex", "Smith", (byte) 22);
        userService.saveUser("Steve", "Doom", (byte) 55);
        userService.dropUsersTable();
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.getAllUsers();
        System.out.println(userService.getAllUsers());
    }
}
