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

    @Column(name = "Program_Name")
    private String Program_Name;

    @Column(name = "Call_Time")
    private Integer Call_Time;

    @Column(name = "Fee")
    private Integer Fee;  //Ik

    @Column(name = "Charge_Per_Second")
    private Integer Charge_Per_Second;

    @Column(name = "Benefits")
    private String Benefits;

    @OneToMany(mappedBy = "program")
    private Set<PhoneNumber> phoneNumbers;

    public Set<PhoneNumber> getPhoneNumbers() { return phoneNumbers; }

    public Program() {
        super();
    }

    public Program(String Program_Name, Integer Call_Time, Integer Fee, Integer Charge_Per_Second) {
        this.Program_Name = Program_Name;
        this.Call_Time = Call_Time;
        this.Fee = Fee;
        this.Charge_Per_Second = Charge_Per_Second;
    }

    public void setBenefits(String Benefits) {
        this.Benefits = Benefits;
    }

    public int getId() {
        return id;
    }

    public String getBenefits() {
        return Benefits;
    }

    public String getProgramName() {
        return Program_Name;
    }

    public void setProgram_Name(String Program_Name) {
        this.Program_Name = Program_Name;
    }

    public int getCall_Time() {
        return Call_Time;
    }

    public void setCall_Time(int Call_Time) {
        this.Call_Time = Call_Time;
    }

    public int getFee() {
        return Fee;
    }

    public void setFee(int Fee) {
        this.Fee = Fee;
    }

    public int getCharge_Per_Second() {
        return Charge_Per_Second;
    }

    public void setCharge_Per_Second(int Charge_Per_Second) {
        this.Charge_Per_Second = Charge_Per_Second;
    }

    public void printProgram() {
        System.out.println(
            "PROGRAM INFO\n" +
            "Name: " + Program_Name + "\n" +
            "Call Time: " + Call_Time + "\n" +
            "Invoice: " + Fee + "\n" +
            "\n"
            );
    }



}
