package com.hibernate.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Column(name = "age")
    private int age;
    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(int id, String username, LocalDate birthdate, int age, String email) {
        this.id = id;
        this.username = username;
        this.birthdate = birthdate;
        this.age = age;
        this.email = email;
    }

    public User(String username, LocalDate birthdate, int age, String email) {
        this.username = username;
        this.birthdate = birthdate;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getLocalDate() {
        return birthdate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.birthdate = localDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", birthdate=" + birthdate
                + ", age=" + age
                + ", email='" + email + '\''
                + '}';
    }
}
