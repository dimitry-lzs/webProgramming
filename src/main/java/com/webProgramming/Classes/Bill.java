package com.webProgramming.Classes;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "month")
    private int month; //1-12

    @ManyToOne
    @JoinColumn(name = "number")
    private PhoneNumber phone_number;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "bill")
    private Set<Call> calls;   //An array that contains calls.

    @Column(name = "paid")
    private boolean paid;   //True if the bill is paid, false if it is not paid.


    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public PhoneNumber getPhonenumber() {
        return phone_number;
    }
    public void setPhonenumber(PhoneNumber phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public Set<Call> getCalls() {
        return calls;
    }

    public void setCalls(Set<Call> calls) {
        this.calls = calls;
    }

    public Bill(int month, PhoneNumber phoneNumber) {
        this.month = month;
        this.phone_number = phoneNumber;
    }

    public void printBill() {
        System.out.println(
            "BILL INFO\n" +
            "Month: " + this.month
        );
        this.phone_number.printPhoneNumber();
        // for (int i = 0; i < calls.length; i++) {
        //     calls[i].printCall();
        // }
    }

}
