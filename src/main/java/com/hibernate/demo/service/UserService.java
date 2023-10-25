package com.hibernate.demo.service;

import com.hibernate.demo.dao.IUserDao;
import com.hibernate.demo.dao.UserDao;
import com.hibernate.demo.model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserService {
    private SessionFactory sessionFactory;
    private IUserDao userDao;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        userDao = new UserDao(sessionFactory);
    }

    public void insertUser(User user) {
        System.out.println("Inserting user");
        userDao.insert(user);
        System.out.println("Successfully inserted user with id of " + user.getId());
    }

    public void updateUser(User user, String[] params) {
        System.out.println("Updating user");
        userDao.update(user, params);
        System.out.println("Successfully updated user with id of " + user.getId());
    }

    public void updateUserWithAPI(User user, String[] params) {
        System.out.println("Updating user");
        userDao.updateWithAPI(user, params);
        System.out.println("Successfully updated user with id of " + user.getId());
    }

    public void deleteUser(User user) {
        System.out.println("Deleting user");
        userDao.delete(user);
        System.out.println("Successfully deleted user with id of " + user.getId());
    }

    public void deleteUserWithCriteria(User user) {
        System.out.println("Deleting user");
        userDao.deleteWithCriteria(user);
        System.out.println("Successfully deleted user with id of " + user.getId());
    }

    public User getUser(int id) {
        System.out.println("Getting user");
        User user = userDao.getUser(id);
        if (user != null) {
            System.out.println("Successfully got user with id of " + user.getId());
        }
        return user;
    }

    public User getUserWithCriteria(int id) {
        System.out.println("Getting user");
        User user = userDao.getUserWithCriteria(id);
        if (user != null) {
            System.out.println("Successfully got user with id of " + user.getId());
        }
        return user;
    }

    public List<User> getAll() {
        System.out.println("Getting all users");
        return userDao.getAll();
    }

    public List<User> getAllWithCriteria() {
        System.out.println("Getting all users with criteria");
        return userDao.getAllWithCriteria();
    }
}
