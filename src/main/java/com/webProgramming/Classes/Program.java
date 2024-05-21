package com.webProgramming.Classes;

//The payment program.
public class Program {

    private String ProgramName;
    private int CallTime;
    private int fee;  //Ik
    private int chargePerSecond;
    private String[] benefits;

    public Program(String programName, int callTime, int fee, int chargePerSecond) {
        this.ProgramName = programName;
        this.CallTime = callTime;
        this.fee = fee;
        this.chargePerSecond = chargePerSecond;
    }

    public void setBenefits(String[] benefits) {
        this.benefits = benefits;
    }

    public String[] getBenefits() {
        return benefits;
    }

    public String getProgramName() {
        return ProgramName;
    }

    public void setProgramName(String programName) {
        this.ProgramName = programName;
    }

    public int getCallTime() {
        return CallTime;
    }

    public void setCallTime(int callTime) {
        this.CallTime = callTime;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getChargePerSecond() {
        return chargePerSecond;
    }

    public void setChargePerSecond(int chargePerSecond) {
        this.chargePerSecond = chargePerSecond;
    }

    public void printProgram() {
        System.out.println(
            "PROGRAM INFO\n" +
            "Name: " + ProgramName + "\n" +
            "Call Time: " + CallTime + "\n" +
            "Invoice: " + fee + "\n" +
            "\n"
            );
    }



}
