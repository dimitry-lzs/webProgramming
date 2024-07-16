package com.webProgramming.models;
import com.webProgramming.models.enums.UserType;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

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

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name="salt")
    private byte[] salt;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public User() {
        super();
    }

    public User(String username, String name, String surname) {
        super();
        this.setUsername(username);
        this.setName(name);
        this.setSurname(surname);
        System.out.println(getName() + " " + getSurname() + " was instantiated.");
    }

    public int getId() {
        return id;
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

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSalt() {
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt); // add the salt
        byte[] hashedPasswordBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPasswordBytes);
    }

    public static int getCount() {
        return 1;
    }

    public static String getRedirectionLink(String type) {
        UserType userType = UserType.valueOf(type);
        System.out.println("User type: " + userType);
        switch (userType) {
            case UserType.CLIENT:
                return "/client/menu.jsp";
            case UserType.SELLER:
                return "/seller/menu.jsp";
            case UserType.ADMIN:
                return "/admin/menu.jsp";
            default:
                return "/index.jsp";
        }
    }
}