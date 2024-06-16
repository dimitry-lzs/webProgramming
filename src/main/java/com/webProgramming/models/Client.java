package com.webProgramming.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends User {
    @Column(name = "AFM", unique = true)
    private String AFM;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_number", referencedColumnName = "number", nullable = false)
    private PhoneNumber phoneNumber;

    @OneToMany(mappedBy = "client")
    private Set<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Set<Bill> getBills() { return bills; }

    public Client() {
        super();
    }

    public Client(String afm, PhoneNumber phoneNumber, String username, String name, String surname) {
        super(username, name, surname);
        this.AFM = afm;
        this.phoneNumber = phoneNumber;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoneNumberValue() {
        if (phoneNumber == null) {
            return "Not set";
        }
        return phoneNumber.getNumber();
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
