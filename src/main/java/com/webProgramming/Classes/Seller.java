package com.webProgramming.Classes;
import com.webProgramming.enums.UserType;
//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sellers")
public class Seller extends User {

    Client[] clientarray = new Client[5];
    PhoneNumber numberarray[];

    public Seller(String username, String name, String surname) {
        super(UserType.SELLER, username, name, surname);
    }

    public Client addClient(String afm, PhoneNumber phonenum, String username, String name, String surname){
        System.out.println("Adding client...");

        //Creating new client.
        Client newclient = new Client(afm, phonenum, username, name, surname);
        //Adding client to client array.
        clientarray[0] = newclient;
        //Return client.
        return newclient;
    }
    public Bill issueBill(PhoneNumber phonenumber, Call[] calls){
        Bill newbill = new Bill(1, phonenumber, calls);
        System.out.println("Issuing bill...");
        return newbill;
    }
    public void changeClientProgram(){
        System.out.println("Changing client program...");
    }
}
