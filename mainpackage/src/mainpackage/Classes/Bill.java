package mainpackage.Classes;

public class Bill {
    private int month; //1-12
    private PhoneNumber phonenumber;
    private Call[] calls;   //An array that contains calls.
    private boolean paid;   //True if the bill is paid, false if it is not paid.

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public PhoneNumber getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(PhoneNumber phonenumber) {
        this.phonenumber = phonenumber;
    }
    public Call[] getCalls() {
        return calls;
    }
    public void setCalls(Call[] calls) {
        this.calls = calls;
    }
    public Bill(int month, PhoneNumber phonenumber, Call[] calls) {
        this.month = month;
        this.phonenumber = phonenumber;
        this.calls = calls;
    }

    public void printBill() {
        System.out.println(
            "BILL INFO\n" +
            "Month: " + this.month
        );
        this.phonenumber.printPhoneNumber();
        for (int i = 0; i < calls.length; i++) {
            calls[i].printCall();
        }
    }

}
