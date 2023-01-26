package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;


public class Main {
    public static void main(String[] args) {




        UserServiceImpl userService =new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();



        userService.saveUser("Alex", "1111", (byte) 1);
        userService.saveUser("Alex2", "222", (byte) 2);
        userService.saveUser("Alex3", "333", (byte) 3);
        userService.saveUser("Alex4", "444", (byte) 4);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

