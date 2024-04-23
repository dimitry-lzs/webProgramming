package mainpackage.Classes;

public class PhoneNumber {
    private String Number;
    private Program Program;


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
