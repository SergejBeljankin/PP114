package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Ivan", "Petrov", (byte) 32);
        userService.saveUser("Ivan", "Ivanov", (byte) 35);
        userService.saveUser("Peter", "Sidorov", (byte) 44);
        userService.saveUser("Sidr", "Kuznetsov", (byte) 48);

        List<User> userList = userService.getAllUsers();

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
