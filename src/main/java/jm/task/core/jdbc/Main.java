package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.createUsersTable();
       //*userDaoJDBC.saveUser("Irina", "Somova", (byte) 38 );
        // userDaoJDBC.saveUser("Marina", "Mamonova", (byte) 32 );

        //userDaoJDBC.dropUsersTable();
        //userDaoJDBC.removeUserById(1);
        //userDaoJDBC.cleanUsersTable();
        userDaoJDBC.getAllUsers();

    }
}
