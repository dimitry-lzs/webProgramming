package com.webProgramming.models;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "phone_number")
public class PhoneNumber implements Serializable {
    @Id
    @Column(name = "number", nullable = false)
    private String number;

    @OneToOne(mappedBy = "phoneNumber")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(mappedBy = "receiver")
    private Set<Call> incomingCalls;

    public Set<Call> getCalls() {  return incomingCalls; }

    @OneToMany(mappedBy = "caller")
    private Set<Call> outgoingCalls;

    public Set<Call> getOutgoingCalls() { return outgoingCalls; }

    public PhoneNumber() {
        super();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public PhoneNumber(String number, Program program) {
        this.number = number;
        this.program = program;
    }
}
