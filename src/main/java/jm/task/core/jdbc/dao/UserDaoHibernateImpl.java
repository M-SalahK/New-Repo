package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Utils;
import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.Query;
import java.util.List;
@Entity

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS triumf (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(15), lastname VARCHAR(15), age INT);");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица создана.");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS triumf;");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица удалена.");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("INSERT INTO triumf (name, lastname, age) VALUES (" + "'" + name + "'," + "'" + lastName + "'," + age + ")");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Пользователь добавлен!");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM kata_db.triumf WHERE id = " + "'" + id + "'" + "LIMIT 1;");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Пользователь " + "id" + "удален");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Utils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            List <User> getUsers = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            System.out.println("Список пользователей:");
            return getUsers;
        }

    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE triumf;");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица очищена!");
        }
    }
}
