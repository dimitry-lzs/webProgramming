package mainpackage.Classes;
import mainpackage.enums.UserType;

public class Seller extends User {
    public Seller() {
        super(UserType.SELLER);
        System.out.println("Seller" + getSurname() + "was created");
    }

    public void addClient(){
    
    }
    public void issueUser(){
    
    }
    public void changeClientProgram(){
    
    }
}
