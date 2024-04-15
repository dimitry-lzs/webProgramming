package mainpackage.Classes;

//The payment program.
public class Program {

    private String ProgramName;
    private int CallTime;
    private int fee;  //Ik

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

    public int getPagio() {
        return fee;
    }
    public void setPagio(int fee) {
        this.fee = fee;
    }

    public Program(String programName, int callTime, int fee) {
        this.ProgramName = programName;
        this.CallTime = callTime;
        this.fee = fee;
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
