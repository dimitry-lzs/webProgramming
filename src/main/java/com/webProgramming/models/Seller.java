package com.webProgramming.models;
import java.util.Random;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sellers")
public class Seller extends User {
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "seller")
    private Set<Client> clients;

    public Set<Client> getClients() { return clients; }

    public Seller() {
        super();
    }

    public Seller(String username, String name, String surname) {
        super(username, name, surname);
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Client createClient(String afm, String username, String name, String surname, String password) {
        String number = "30";
        Random rand = new Random();

        for (int i = 0; i < 9; i++) {
            number += rand.nextInt(10);
        }

        PhoneNumber phoneNumber = new PhoneNumber(number, null);

        Client client = new Client(afm, phoneNumber, username, name, surname);
        client.setPassword(password);
        client.setSeller(this);
        return client;
    }

    public Bill issueBill(PhoneNumber phonenumber, Call[] calls){
        Bill newbill = new Bill(1, phonenumber);
        System.out.println("Issuing bill...");
        return newbill;
    }
    public void changeClientProgram(){
        System.out.println("Changing client program...");
    }
}
