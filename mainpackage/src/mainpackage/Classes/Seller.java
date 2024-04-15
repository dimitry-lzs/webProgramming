package mainpackage.Classes;
import mainpackage.enums.UserType;

public class Seller extends User {

    Client clientarray[];
    PhoneNumber numberarray[];

    public Seller() {
        super(UserType.SELLER);
    }

    public void addClient(PhoneNumber phonenum){
        System.out.println("Adding client...");

        //Creating new phone number.
        Client newclient = new Client();
        


    }
    public void issueUser(){
        System.out.println("Issuing user...");
    }
    public void changeClientProgram(){
        System.out.println("Changing client program...");
    }
}
