package com.webProgramming.Classes;

import com.webProgramming.enums.UserType;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends User {
    @Id
    @Column(name = "AFM")
    private final String AFM;
    @Column(name = "Phone_Number")
    private PhoneNumber Phone_Number;

    @OneToMany(mappedBy = "client")
    private Set<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Set<Bill> getBills() { return bills; }

    public Client(String afm, PhoneNumber phonenumber, String username, String name, String surname) {
        super(UserType.CLIENT, username, name, surname);
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
