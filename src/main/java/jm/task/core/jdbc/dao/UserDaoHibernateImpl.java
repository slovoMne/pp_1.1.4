package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import org.hibernate.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = getSessionFactory();

    private static Session session;
    private static Transaction transaction;
    private String name;
    private String lastName;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        transaction = null;
        session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "PRIMARY KEY (id), " +
                    "name VARCHAR (45), " +
                    "lastname VARCHAR (45), " +
                    "age int)").executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            try{
                if (transaction != null){
                    session.close();
                }
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        session = sessionFactory.openSession();
        transaction = null;
        try{
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            if (transaction != null) {
                transaction.commit();
            }
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            try {
                if (transaction != null) {
                    session.close();
                }
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        session = sessionFactory.openSession();
        transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("Пользователь " + name + " добавлен");
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            try{
                session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.openSession();
        transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE id = :id");
            query.setParameter("id", id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            try{
                session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        transaction = null;
        session = sessionFactory.openSession();
        try {
            transaction = session.getTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.openSession();
        transaction = null;
        try{
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE user;").executeUpdate();
            System.out.println("User-ов нет");
            if (transaction != null) {
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            try {
                if (transaction != null) {
                    session.close();
                }
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }
}