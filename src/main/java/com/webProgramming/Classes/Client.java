package com.webProgramming.Classes;
import com.webProgramming.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Id;


public class Client extends User {

    @Id
    private final String afm;
    @Column(name = "phonenumber")
    private PhoneNumber phonenumber;
    @Column(name = "bills")
    private Bill[] bills;   // An array that contains bills.





    public Client(String afm, PhoneNumber phonenumber, String username, String name, String surname) {
        super(UserType.CLIENT, username, name, surname);
        this.afm = afm;
        this.phonenumber = phonenumber;
    }

    public void setBills(Bill[] bills) {
        this.bills = bills;
    }

    public String getAfm() {
        return afm;
    }

    public PhoneNumber getPhonenumber() {
        return phonenumber;
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
