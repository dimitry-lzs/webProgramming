package com.webProgramming.models;

import java.util.Set;

import com.webProgramming.models.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients", uniqueConstraints = @UniqueConstraint(columnNames = {"AFM"}))
public class Client extends User {
    @Column(name = "AFM")
    private String AFM;

    @Column(name = "Phone_Number")
    private PhoneNumber Phone_Number;

    @OneToMany(mappedBy = "client")
    private Set<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Set<Bill> getBills() { return bills; }

    public Client() {
        super();
    }

    public Client(String afm, PhoneNumber phonenumber, String username, String name, String surname) {
        super(username, name, surname);
        this.AFM = afm;
        this.Phone_Number = phonenumber;
    }

    public void setBills(Bill[] bills) {
        // this.bills = bills;
    }

    public String getAfm() {
        return AFM;
    }

    public PhoneNumber getPhonenumber() {
        return Phone_Number;
    }

    public void viewBills() {
        // for (int i = 0; i < bills.length; i++) {
        //     bills[i].printBill();
        // }
    }

    public void viewLastBill() {

    }

    public void payBill() {
        // bills[bills.length - 1].setPaid(true);
    }
}
