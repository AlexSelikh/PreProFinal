package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45),lastname VARCHAR(45),age TINYINT)");
            query.executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
            System.out.println("Таблица существует");
        }
    }


    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE users");
            query.executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
            System.out.println("таблицы нет ");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (PersistenceException e) {
            System.out.println("неправильно добавили пользователя");
        }

    }


    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Такого пользователя нет");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            users = session.createQuery("FROM User").getResultList();
            for (User el : users) {
                System.out.println(el);
            }
            session.getTransaction().commit();

        } catch (PersistenceException e) {
            System.out.println("неправильно добавили пользователя");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createQuery("delete  User").executeUpdate();
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Такого пользователя нет");
        }
    }
}

