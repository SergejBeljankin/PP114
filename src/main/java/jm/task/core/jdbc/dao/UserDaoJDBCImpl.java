package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connectUSER;
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `pre_project`.`user_dao` (\n" +
            "  `id_table` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `lastname` VARCHAR(45) NULL,\n" +
            "  `age` INT NOT NULL,\n" +
            "  PRIMARY KEY (`id_table`));";
    private final String DROP_TABLE = "DROP TABLE IF EXISTS `pre_project`.`user_dao`;";
    private final String INSERT_INTO = "INSERT INTO `pre_project`.`user_dao` VALUES(?,?,?,?);";
    private final String CLEAN_TABLE ="TRUNCATE TABLE `pre_project`.`user_dao`;";
    private final String SELECT_FROM = "SELECT * FROM `pre_project`.`user_dao`;";
    private final String DELETE_ROW = "DELETE FROM `pre_project`.`user_dao` WHERE `id_table` = ?";

    public UserDaoJDBCImpl() {
        connectUSER = Util.getConnection();
    }

    @Override
    public void createUsersTable() {
        try(Statement statement = connectUSER.createStatement()){
            statement.execute(CREATE_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.printf("Ошибка при создании таблицы");
        }
    }
    @Override
    public void dropUsersTable() {
        try(Statement statement = connectUSER.createStatement()){
            statement.execute(DROP_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.printf("Ошибка при удалении таблицы");
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement preparedStatement = connectUSER.prepareStatement(INSERT_INTO)){

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.printf("Ошибка при записи имени, фамилии, возраста");
        }
    }
    @Override
    public void removeUserById(long id) {

        try( PreparedStatement prStatement = connectUSER.prepareStatement(DELETE_ROW)) {
            prStatement.setInt(1, (int) id);
            prStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.printf("Ошибка при удалении юзверга");
        }

    }
    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try(
            PreparedStatement pStatement = connectUSER.prepareStatement(SELECT_FROM);
            ResultSet rs = pStatement.executeQuery();){
            while (rs.next()){
                long id = rs.getInt(1);
                String name = rs.getString(2);
                String lastname = rs.getString(3);
                byte age = rs.getByte(4);
                User user = new User(name, lastname, age);
                user.setId(id);
                allUsers.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.printf("Ошибка при чтении таблицы");
        }

        return allUsers;
    }
    @Override
    public void cleanUsersTable() {
        try(Statement statement = connectUSER.createStatement()){
            statement.execute(CLEAN_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.printf("Ошибка очистке таблицы");
        }

    }
}
