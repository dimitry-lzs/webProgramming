package com.webProgramming.models;
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

    // public Client addClient(String afm, int phonenum, String username, String name, String surname){
    //     System.out.println("Adding client...");

    //     // //Creating new client.
    //     Client newclient = new Client(afm, phonenum, username, name, surname);
    //     // //Adding client to client array.
    //     // clientarray[0] = newclient;
    //     // //Return client.
    //     return newclient;
    // }
    public Client createClient(String afm, int phonenumber, String username, String name, String surname, String password){
        Client client = new Client(afm, phonenumber, username, name, surname);
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
