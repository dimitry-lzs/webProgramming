
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
    private long callStartTs;

    @Column(name = "callEndTs")
    private long callEndTs;

    @Column(name = "duration")
    private long duration;

    public Call(PhoneNumber caller, PhoneNumber receiver, long callStartTs, long callEndTs) {
        this.callStartTs = callStartTs;
        this.caller = caller;
        this.receiver = receiver;
        this.callEndTs = callEndTs;
        this.duration = this.callEndTs - this.callStartTs;
    }
    public Call(){}

    public long getDuration() {
        return this.duration;
    }

    public PhoneNumber getCaller() {
        return this.caller;
    }

    public PhoneNumber getReceiver() {
        return this.receiver;
    }

    public long getCallTimestamp() {
        return this.callStartTs;
    }

    public long getEndTimestamp() {
        return this.callEndTs;
    }

    public Bill getBill() {
        return this.bill;
    }
}