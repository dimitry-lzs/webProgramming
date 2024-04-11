package mainpackage.Classes;
import mainpackage.enums.UserType;

public class Client extends User {
    public Client() {
        super(UserType.CLIENT);
        System.out.println("Client" + getSurname() + "was created");
    }
}
