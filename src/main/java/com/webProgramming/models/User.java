package com.webProgramming.models;
import java.io.Serializable;

import com.webProgramming.models.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "type")
    private UserType type;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public User() {
        System.out.println("User was instantiated.");
    }

    public User(UserType type, String username, String name, String surname) {
        this.setType(type);
        this.setUsername(username);
        this.setName(name);
        this.setSurname(surname);
        System.out.println(type + " " + getName() + " " + getSurname() + " was instantiated.");
    }

    public void register() {
        System.out.println("User " + type + " registered");
    }

    public void login() {
        System.out.println("User " + type + " logged in");
    }

    public void logout() {
        System.out.println("User " + type + " logged out");
    }

    private void setType(UserType type) {
        this.type = type;
    }

    public UserType getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public static int getCount() {
        return 1;
    }
}