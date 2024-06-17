package com.webProgramming.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "programs")
public class Program implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name = "name")
    private String name;

    @Column(name = "call_time")
    private int callTime;

    @Column(name = "fee")
    private int fee;  //Ik

    @Column(name = "charge_per_second")
    private int chargePerSecond;

    @Column(name = "benefits")
    private String benefits;

    @OneToMany(mappedBy = "program")
    private Set<PhoneNumber> phoneNumbers;

    public Set<PhoneNumber> getPhoneNumbers() { return phoneNumbers; }

    public Program() {
        super();
    }

    public Program(String name, int callTime, int fee, int chargePerSecond) {
        this.name = name;
        this.callTime = callTime;
        this.fee = fee;
        this.chargePerSecond = chargePerSecond;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCall_Time(int callTime) {
        this.callTime = callTime;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setChargePerSecond(int chargePerSecond) {
        this.chargePerSecond = chargePerSecond;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public int getId() {
        return id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    public int getCallTime() {
        return callTime;
    }

    public int getFee() {
        return fee;
    }

    public int getChargePerSecond() {
        return chargePerSecond;
    }

    public String getBenefits() {
        return benefits;
    }

    public void printProgram() {
        System.out.println(
            "PROGRAM INFO\n" +
            "Name: " + name + "\n" +
            "Call Time: " + callTime + "\n" +
            "Invoice: " + fee + "\n" +
            "\n"
        );
    }
}
