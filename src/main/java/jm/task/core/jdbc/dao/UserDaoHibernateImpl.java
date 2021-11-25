package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private static Session session;
    private static Transaction transaction;
    private String name;
    private String lastName;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS mydbtest (id BIGINT NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), name VARCHAR (45), lastname VARCHAR (45), INT age)");
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
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS mydbtest;").executeUpdate();
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
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
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
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
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
        Transaction transaction = null;
        Session session = getSessionFactory().openSession();
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
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE mydbtest;").executeUpdate();
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

