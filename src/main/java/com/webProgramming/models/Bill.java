package com.webProgramming.models;

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

    @Column(name = "charge")
    private double charge;  //The total charge of the bill.

    @Column(name = "call_duration")
    private int call_duration;  //The total call duration of the bill.

    @Column(name = "program_name")
    private String program_name;    //The name of the program.

    public Boolean isPaid() {
        return paid;
    }

    public int getID(){
        return id;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getProgramName() {
        return program_name;
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public double getCharge() {
        return charge;
    }
    public void setCharge(double charge) {
        this.charge = charge;
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

    public Client getClient() {
        return client;
    }

    public void setCalls(Set<Call> calls) {
        this.calls = calls;
    }

    public Bill() {
    }

    public Bill(Client client, PhoneNumber phoneNumber, int month, Boolean paid, double charge) {
        this.month = month;
        this.phone_number = phoneNumber;
        this.client = client;
        this.paid = paid;
        this.charge = charge;
    }

    public int getCallDuration() {
        return this.call_duration;
    }

    public  void setCallDuration(int call_duration) {
        this.call_duration = call_duration;
    }


    public void setProgramName(String program_name) {
        this.program_name=program_name;
    }

}
