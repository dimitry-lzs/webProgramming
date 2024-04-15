package mainpackage.Classes;
import mainpackage.enums.UserType;

public class Client extends User {
    private String afm;
    private PhoneNumber phonenumber;
    private Bill[] bills;   // An array that contains bills.

    public Client(String afm, PhoneNumber phonenumber) {
        super(UserType.CLIENT);
        this.afm = afm;
        this.phonenumber = phonenumber;
    }

    public void setBills(Bill[] bills) {
        this.bills = bills;
    }

    public String getAfm() {
        return afm;
    }

    public PhoneNumber getPhonenumber() {
        return phonenumber;
    }

    public Bill[] getBills() {
        return bills;
    }

    public void viewBills() {
        for (int i = 0; i < bills.length; i++) {
            bills[i].printBill();
        }
    }

    public void viewLastBill() {
        bills[bills.length - 1].printBill();
    }
}
