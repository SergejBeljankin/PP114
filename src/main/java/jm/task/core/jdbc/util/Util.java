package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DbUrl = "jdbc:mysql://localhost:3306/pre_project"; //INIT
    private static String DbUserName = "root";
    private static String DbPass ="123qwe";
    private static SessionFactory sessionFactory;

    public static SessionFactory getConnection() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.URL, DbUrl);
                settings.put(Environment.USER, DbUserName);
                settings.put(Environment.PASS, DbPass);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Ошибка при подключиении");
                e.printStackTrace();
            }
        }
        return sessionFactory;

    }


    /*

    public static Connection getConnection() {
        Connection connect = null;
        try{
            connect = DriverManager.getConnection(DbUrl, DbUserName, DbPass);
            if(!connect.isClosed()){
                System.out.println("Удачное соединение");
            }
        } catch (SQLException e){
            e.getStackTrace();
        }
        return connect;
    }

     */
}
