package com.webProgramming.Classes;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "phone_number")
public class PhoneNumber {

    @Id
    @Column(name = "number")
    private String Number;

    @Column(name = "program")
    private Program Program;

    @OneToMany(mappedBy = "receiver")
    private Set<Call> incomingCalls;

    public Set<Call> getCalls() {  return incomingCalls; }

    @OneToMany(mappedBy = "caller")
    private Set<Call> outgoingCalls;

    public Set<Call> getOutgoingCalls() { return outgoingCalls; }

    public String getNumber() {
        return Number;
    }
    public void setNumber(String number) {
        Number = number;
    }

    public Program getProgram() {
        return Program;
    }
    public void setProgram(Program program) {
        Program = program;
    }
    public PhoneNumber(String number, Program program) {
        Number = number;
        Program = program;
    }

    public void printPhoneNumber() {
        System.out.println(
            "PHONE NUMBER INFO\n" +
            "Number: " + Number + "\n"
        );
        Program.printProgram();
    }

    public Call call(PhoneNumber phonenumber, int duration) {
        Call newcall = new Call(this, phonenumber, 1, 2);
        System.out.println("Calling...");
        return newcall;
    }
}
