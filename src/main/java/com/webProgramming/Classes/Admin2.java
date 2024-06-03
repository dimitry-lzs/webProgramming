package com.webProgramming.Classes;

import com.webProgramming.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admins")
public class Admin2 extends User {

    @Column(name = "type")
    private UserType type;
    
    //DEN YPARXEI THESI STON PINAKA USERS GIA NA TA VALOUME. XREIAZONTAI??
    private Program[] programs;
    private Seller[] sellers;
    //

    public Admin2(String username, String name, String surname) {
        super(UserType.ADMIN, username, name, surname);
        System.out.println("Admin is " + getName() + " " + getSurname());
    }

    public Program createProgram(String programName, int callTime, int fee, int chargePerSecond) {
        Program program = new Program(programName, callTime, fee, chargePerSecond);
        Program[] newPrograms = new Program[programs.length + 1];

        for (int i = 0; i < programs.length; i++) {
            newPrograms[i] = programs[i];
        }

        newPrograms[programs.length] = program;
        this.setPrograms(newPrograms);
        return program;
    }

    public Seller createSeller(String username, String name, String surname) {
        Seller seller = new Seller(username, name, surname);
        Seller[] newSellers = new Seller[sellers.length + 1];

        for (int i = 0; i < sellers.length; i++) {
            newSellers[i] = sellers[i];
        }

        newSellers[sellers.length] = seller;
        this.setSellers(newSellers);
        return seller;
    }

    public void setSellers(Seller[] sellers) {
        this.sellers = sellers;
    }

    public void setPrograms(Program[] programs) {
        this.programs = programs;
    }

    public Program[] getPrograms() {
        return programs;
    }

    public Seller[] getSellers() {
        return sellers;
    }

    public void deleteSeller(String username){
        Seller[] newSellers = new Seller[sellers.length - 1];
        int j = 0;
        for (int i = 0; i < sellers.length; i++) {
            if (sellers[i].getUsername().equals(username)) {
                System.out.println("Seller " + sellers[i].getUsername() + " deleted");
                continue;
            }
            newSellers[j] = sellers[i];
            j++;
        }
        this.setSellers(newSellers);
    }

    public void deleteProgram(String programName){
        Program[] newPrograms = new Program[programs.length - 1];
        int j = 0;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].getProgramName().equals(programName)) {
                System.out.println("Program " + programs[i].getProgramName() + " deleted");
                continue;
            }
            newPrograms[j] = programs[i];
            j++;
        }
        this.setPrograms(newPrograms);
    }
}
