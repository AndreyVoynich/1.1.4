package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("(Id INT PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(20), lastname VARCHAR(20), age INT)")
                    .addEntity(User.class)
                    .executeUpdate();
            session.getTransaction().commit();
        }
        finally {
            getSessionFactory().close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users")
                    .addEntity(User.class)
                    .executeUpdate();
            session.getTransaction().commit();
        }
        finally {
            getSessionFactory().close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        }
        finally {
            getSessionFactory().close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        }
        finally {
            getSessionFactory().close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = Util.getSessionFactory().openSession();
            List<User> allUsers = session.createSQLQuery
                            ("SELECT * FROM User").list();
            session.close();
            return allUsers;
        }
        finally {
            getSessionFactory().close();
        }
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("truncate table users")
                    .executeUpdate();
            session.getTransaction().commit();
        }
        finally {
            getSessionFactory().close();
        }
    }
}