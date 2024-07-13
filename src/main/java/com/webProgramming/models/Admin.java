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
        // this.programs = new Program[0]; // Initialize programs as an empty array
        // this.sellers = new Seller[0]; // Initialize sellers as an empty array
        System.out.println("Admin is " + getName() + " " + getSurname());
    }

    public Program createProgram(String programName, int callTime, int fee, int chargePerSecond) {
        Program program = new Program(programName, callTime, fee, chargePerSecond);
        // Program[] newPrograms = new Program[programs.length + 1];

        // for (int i = 0; i < programs.length; i++) {
        //     newPrograms[i] = programs[i];
        // }

        // newPrograms[programs.length] = program;
        // this.setPrograms(newPrograms);
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

    public void setSellers(Seller[] sellers) {
        // this.sellers = sellers;
    }

    public void setPrograms(Program[] programs) {
        // this.programs = programs;
    }

    public void deleteSeller(String username){
        // Seller[] newSellers = new Seller[sellers.length - 1];
        // int j = 0;
        // for (int i = 0; i < sellers.length; i++) {
        //     if (sellers[i].getUsername().equals(username)) {
        //         System.out.println("Seller " + sellers[i].getUsername() + " deleted");
        //         continue;
        //     }
        //     newSellers[j] = sellers[i];
        //     j++;
        // }
        // this.setSellers(newSellers);
    }
    public void deleteProgram(String programName){
        // Program[] newPrograms = new Program[programs.length - 1];
        // int j = 0;
        // for (int i = 0; i < programs.length; i++) {
        //     if (programs[i].getProgramName().equals(programName)) {
        //         System.out.println("Program " + programs[i].getProgramName() + " deleted");
        //         continue;
        //     }
        //     newPrograms[j] = programs[i];
        //     j++;
        // }
        // this.setPrograms(newPrograms);
    }
}
