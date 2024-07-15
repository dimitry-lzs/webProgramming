
package com.webProgramming.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "calls", uniqueConstraints = @UniqueConstraint(columnNames = { "caller", "callStartTs" }))
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "caller_number")
    private PhoneNumber caller;

    @ManyToOne
    @JoinColumn(name = "receiver_number")
    private PhoneNumber receiver;

    @Column(name = "callStartTs")
    private int callStartTs;

    @Column(name = "callEndTs")
    private int callEndTs;

    @Column(name = "duration")
    private int duration;

    public Call(PhoneNumber caller, PhoneNumber receiver, int callStartTs, int callEndTs) {
        this.callStartTs = callStartTs;
        this.caller = caller;
        this.receiver = receiver;
        this.callEndTs = callEndTs;
        this.duration = this.callEndTs - this.callStartTs;
    }
    public Call(){}

    public int getDuration() {
        return this.duration;
    }

    public PhoneNumber getCaller() {
        return this.caller;
    }

    public PhoneNumber getReceiver() {
        return this.receiver;
    }

    public int getCallTimestamp() {
        return this.callStartTs;
    }

    public int getEndTimestamp() {
        return this.callEndTs;
    }

    public Bill getBill() {
        return this.bill;
    }


    public void printCall() {
        System.out.println(
                "CALL INFO\n" +
                        "Duration: " + this.duration + "\n");
    }
}