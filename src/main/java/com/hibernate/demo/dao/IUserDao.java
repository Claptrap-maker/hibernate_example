package com.hibernate.demo.dao;

import com.hibernate.demo.model.User;

import java.util.List;

public interface IUserDao {
    void insert(User user);

    void update(User user, String[] params);

    void updateWithAPI(User user, String[] params);

    void delete(User user);

    void deleteWithCriteria(User user);

    User getUser(int id);

    User getUserWithCriteria(int id);

    List<User> getAll();

    List<User> getAllWithCriteria();
}
