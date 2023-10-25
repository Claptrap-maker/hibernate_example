package com.hibernate.demo;

import com.hibernate.demo.service.UserService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        UserService userService = new UserService(sessionFactory);
        //test getAll()
        //userService.getAll();

        // test getAllWithCriteria()
        //userService.getAllWithCriteria();

        //test insert()
//        userService.insertUser(new User(
//                "Julia",
//                LocalDate.of(2003, 12, 8),
//                20,
//                "julia@gmail.com"));

        //test update()
//        String[] params = {"Helen", "2004-01-15", "9", "helen@mail.com"};
//        userService.updateUser(new User(1,"", null, 0, ""), params);

        //test updateWithAPI()
//        String[] params = {"Helen123", "2013-01-15", "10", "helen123@mail.com"};
//        userService.updateUserWithAPI(new User(1,"", null, 0, ""), params);

        //test delete()
        //userService.deleteUser(new User(1, "", null, 0, ""));

        //test deleteWithCriteria()
        //userService.deleteUserWithCriteria(new User(2, "", null, 0, ""));

        //test getUserWithCriteria()
        //userService.getUserWithCriteria(3);

        //test getUser()
        userService.getUser(3);

        sessionFactory.close();

    }
}
