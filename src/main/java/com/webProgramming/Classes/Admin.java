package com.webProgramming.Classes;
import java.util.Set;

import com.webProgramming.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {

    @Column(name = "type")
    private UserType type;

    @OneToMany(mappedBy = "admin")
    private Set<Program> programs;

    public Set<Program> getPrograms() { return programs; }

    @OneToMany(mappedBy = "admin")
    private Set<Seller> sellers;

    public Set<Seller> getSellers() { return sellers; }

    public Admin(String username, String name, String surname) {
        super(UserType.ADMIN, username, name, surname);
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

    public Seller createSeller(String username, String name, String surname) {
        Seller seller = new Seller(username, name, surname);
        // Seller[] newSellers = new Seller[sellers.length + 1];

        // for (int i = 0; i < sellers.length; i++) {
        //     newSellers[i] = sellers[i];
        // }

        // newSellers[sellers.length] = seller;
        // this.setSellers(newSellers);
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
