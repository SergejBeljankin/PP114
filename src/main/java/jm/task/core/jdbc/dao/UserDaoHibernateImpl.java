package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getConnection();
    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS `pre_project`.`user_dao` (\n" +
                "  `id_table` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id_table`));").executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS `pre_project`.`user_dao`;").executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("INSERT INTO `pre_project`.`user_dao`(`name`, `lastName`, `age`) VALUES(\""
                + name + "\", \"" + lastName + "\", \"" +  age + "\");").executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM `pre_project`.`user_dao` WHERE `id_table` = " + id + ";").executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        List result = session.createSQLQuery("SELECT * FROM `pre_project`.`user_dao`;").list();

        for (int i = 0; i < result.size(); i++) {
            int id = (int) ((Object []) result.get(i))[0];
            String name = (String) ((Object []) result.get(i))[1];
            String lastname = (String) ((Object []) result.get(i))[2];
            int age = (int) ((Object []) result.get(i))[3];
            User user = new User(name, lastname, (byte) age);
            user.setId((long) id);
            userList.add(user);
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE `pre_project`.`user_dao`;").executeUpdate();

        transaction.commit();
        session.close();
    }

}
