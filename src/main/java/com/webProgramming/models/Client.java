package com.webProgramming.models;

import java.util.Set;

import com.webProgramming.models.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends User {
    @Column(name = "AFM", unique = true)
    private String AFM;

    @Column(name = "Phone_Number")
    private int Phone_Number;

    @OneToMany(mappedBy = "client")
    private Set<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Set<Bill> getBills() { return bills; }

    public Client() {
        super();
    }

    public Client(String afm, int phonenumber, String username, String name, String surname) {
        super(username, name, surname);
        this.AFM = afm;
        this.Phone_Number = phonenumber;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setBills(Bill[] bills) {
        // this.bills = bills;
    }

    public void setAfm(String afm) {
        this.AFM = afm;
    }

    public String getAfm() {
        return AFM;
    }

    public int getPhonenumber() {
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
