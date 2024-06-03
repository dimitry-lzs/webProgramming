package com.webProgramming.Classes;

import com.webProgramming.enums.UserType;

import jakarta.persistence.Entity;

@Entity
public class Admin2 extends User {
    public Admin2(String username, String name, String surname) {
        super(UserType.ADMIN, username, name, surname);
        System.out.println("Admin is " + getName() + " " + getSurname());
    }
}
