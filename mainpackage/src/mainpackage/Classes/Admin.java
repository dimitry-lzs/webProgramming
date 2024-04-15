package mainpackage.Classes;
import mainpackage.enums.UserType;

public class Admin extends User {
    public Admin(String username, String name, String surname) {
        super(UserType.ADMIN, username, name, surname);
        System.out.println("Admin" + getSurname() + "was created");
    }

    public void addSeller(){
    
    }
    public void deleteSeller(){
    
    }
    public void createProgram(){
    
    }
}
