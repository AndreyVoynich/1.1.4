package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {


        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 24);
        userService.saveUser("Name2", "LastName2", (byte) 27);
        userService.saveUser("Name3", "LastName3", (byte) 34);
        userService.saveUser("Name4", "LastName4", (byte) 39);

        userService.removeUserById(3);
        userService.getAllUsers();
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
