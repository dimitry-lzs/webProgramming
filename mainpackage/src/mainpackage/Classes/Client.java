package mainpackage.Classes;
import mainpackage.enums.UserType;

public class Client extends User {
    private String afm;
    private PhoneNumber phonenumber;

    public Client(String afm, PhoneNumber phonenumber) {
        super(UserType.CLIENT);
        this.afm = afm;
        this.phonenumber = phonenumber;
    }
}
