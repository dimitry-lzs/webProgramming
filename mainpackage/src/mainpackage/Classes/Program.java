package mainpackage.Classes;

//The payment program.
public class Program {

    private String ProgramName;
    private int CallTime;
    private int Pagio;  //Idk

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
        return Pagio;
    }
    public void setPagio(int pagio) {
        this.Pagio = pagio;
    }

    public Program(String programName, int callTime, int pagio) {
        ProgramName = programName;
        CallTime = callTime;
        Pagio = pagio;
    }

    public void printProgram() {
        System.out.println(
            "PROGRAM INFO\n" +
            "Name: " + ProgramName + "\n" +
            "Call Time: "+ CallTime + "\n" +
            "Invoice: "+ Pagio + "\n" +
            "\n"
            );
    }



}
