package com.hibernate.demo.dao;

import com.hibernate.demo.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    private List<User> users = new ArrayList<>();

    private SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user, String[] params) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "UPDATE User set username = :username, birthdate = :birthdate, "
                    + "age = :age, email = :email " + "WHERE id = :userId";
            Query query = session.createQuery(hql);
            query.setParameter("username", params[0]);
            query.setParameter("birthdate", LocalDate.parse(params[1], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            query.setParameter("age", Integer.parseInt(params[2]));
            query.setParameter("email", params[3]);
            query.setParameter("userId", user.getId());
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateWithAPI(User user, String[] params) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
            Root<User> root = criteriaUpdate.from(User.class);
            criteriaUpdate.set("username", params[0]);
            criteriaUpdate.set("birthdate", LocalDate.parse(params[1], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            criteriaUpdate.set("age", Integer.parseInt(params[2]));
            criteriaUpdate.set("email", params[3]);
            criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), user.getId()));
            int result = session.createQuery(criteriaUpdate).executeUpdate();
            System.out.println("Rows affected: " + result);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "delete User where id = :userId";
            int result = session.createQuery(hql)
                    .setParameter("userId", user.getId())
                    .executeUpdate();
            System.out.println("Rows affected: " + result);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteWithCriteria(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
            Root<User> root = criteriaDelete.from(User.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), user.getId()));
            int result = session.createQuery(criteriaDelete).executeUpdate();
            System.out.println("Rows affected: " + result);
            session.getTransaction().commit();
        }
    }

    @Override
    public User getUser(int id) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            user = session.createQuery("select u from User u where id = :id", User.class)
                    .setParameter("id", id).list().get(0);
            System.out.println(user);
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public User getUserWithCriteria(int id) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
            Query<User> query = session.createQuery(criteriaQuery);
            user = query.getResultList().get(0);
            System.out.println(user);
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            users = session.createQuery("select u from User u", User.class)
                    .list();
            users.forEach(System.out::println);
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public List<User> getAllWithCriteria() {
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            Query<User> query = session.createQuery(criteriaQuery);
            users = query.getResultList();
            users.forEach(System.out::println);
            session.getTransaction().commit();
        }
        return users;
    }
}
