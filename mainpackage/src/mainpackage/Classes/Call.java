
package mainpackage.Classes;

//A Call that was made by a user. Has duration.
public class Call {

    private int duration;
    private Program program;
    //Also needs properties for start/end time, and date(day, month, year).

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int d) {
        this.duration = d;
    }

    public Call(int duration, Program program) {
        this.duration = duration;
        this.program = program;
    }

   public void printCall() {
    System.out.println(
        "CALL INFO\n" +
        "Duration: " + this.duration + "\n"  
    );
    this.program.printProgram();
   }

}