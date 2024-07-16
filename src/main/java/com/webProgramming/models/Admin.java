package com.webProgramming.models;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {
    @OneToMany(mappedBy = "admin")
    private Set<Program> programs;

    public Set<Program> getPrograms() { return programs; }

    @OneToMany(mappedBy = "admin")
    private Set<Seller> sellers;

    public Set<Seller> getSellers() { return sellers; }

    public void setSellers(Set<Seller> sellers) {
        this.sellers = sellers;
    }

    public Admin() {
        super();
    }

    public Admin(String username, String name, String surname) {
        super(username, name, surname);
        System.out.println("Admin is " + getName() + " " + getSurname());
    }

    public Program createProgram(String programName, int callTime, int fee, int chargePerSecond) {
        Program program = new Program(programName, callTime, fee, chargePerSecond);
        return program;
    }

    public Seller createSeller(String username, String name, String surname, String password) throws NoSuchAlgorithmException {
        Seller seller = new Seller(username, name, surname);
        byte[] salt=seller.generateSalt();
        seller.setSalt(salt);
        seller.setPassword(User.hashPassword(password, salt));
        seller.setAdmin(this);
        return seller;
    }
}
