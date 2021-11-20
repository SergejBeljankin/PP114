package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static String DbUrl = "jdbc:mysql://localhost:3306/pre_project"; //INIT
    private static String DbUserName = "root";
    private static String DbPass ="123qwe";



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
}
