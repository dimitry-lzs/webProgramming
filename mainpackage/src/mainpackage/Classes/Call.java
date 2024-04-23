
package mainpackage.Classes;

//A Call that was made by a user. Has duration.
public class Call {

    private final PhoneNumber caller;
    private final PhoneNumber receiver;
    private final int callStartTs;
    private final int callEndTs;
    private final int duration;

    public Call(PhoneNumber caller, PhoneNumber receiver, int callStartTs, int callEndTs) {
        this.callStartTs = callStartTs;
        this.caller = caller;
        this.receiver = receiver;
        this.callEndTs = callEndTs;
        this.duration = this.callEndTs - this.callStartTs;
    }

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

   public void printCall() {
    System.out.println(
        "CALL INFO\n" +
        "Duration: " + this.duration + "\n"
    );
   }
}