package mainpackage.Classes;
import mainpackage.enums.UserType;

public class Seller extends User {

    Client[] clientarray = new Client[5];
    PhoneNumber numberarray[];

    public Seller(String username, String name, String surname) {
        super(UserType.SELLER, username, name, surname);
        System.out.println("Seller" + getSurname() + "was created");
    }

    public Client addClient(String afm, PhoneNumber phonenum, String username, String name, String surname){
        System.out.println("Adding client...");

        //Creating new client.
        Client newclient = new Client(afm, phonenum, "ClientUsername", "ClientName", "ClientSurname");
        //Adding client to client array.
        clientarray[0] = newclient;
        //Return client.
        return newclient;
    }
    public void issueUser(){
        System.out.println("Issuing user...");
    }
    public void changeClientProgram(){
        System.out.println("Changing client program...");
    }
}
