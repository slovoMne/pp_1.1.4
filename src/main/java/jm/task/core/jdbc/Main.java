package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) throws Exception {

        UserDaoHibernateImpl userService = new UserDaoHibernateImpl();
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
