package com.webProgramming.Classes;
import com.webProgramming.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends User {

    @Id
    @Column(name = "AFM")
    private final String AFM;
    @Column(name = "Phone_Number")
    private PhoneNumber Phone_Number;
    @Column(name = "Bills")
    private Bill[] bills;   // An array that contains bills.





    public Client(String afm, PhoneNumber phonenumber, String username, String name, String surname) {
        super(UserType.CLIENT, username, name, surname);
        this.AFM = afm;
        this.Phone_Number = phonenumber;
    }

    public void setBills(Bill[] bills) {
        this.bills = bills;
    }

    public String getAfm() {
        return AFM;
    }

    public PhoneNumber getPhonenumber() {
        return Phone_Number;
    }

    public Bill[] getBills() {
        return bills;
    }

    public void viewBills() {
        for (int i = 0; i < bills.length; i++) {
            bills[i].printBill();
        }
    }

    public void viewLastBill() {
        bills[bills.length - 1].printBill();
    }

    public void payBill() {
        bills[bills.length - 1].setPaid(true);
    }
}
