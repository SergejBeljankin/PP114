package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Ivan", "Petrov", (byte) 32);
        userDao.saveUser("Ivan", "Ivanov", (byte) 35);
        userDao.saveUser("Peter", "Sidorov", (byte) 44);
        userDao.saveUser("Sidr", "Kuznetsov", (byte) 48);

        List<User> userList = userDao.getAllUsers();

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
