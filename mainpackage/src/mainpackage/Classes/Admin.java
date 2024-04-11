package mainpackage.Classes;
import mainpackage.enums.UserType;

public class Admin extends User {
    public Admin() {
        super(UserType.ADMIN);
        System.out.println("Admin" + getSurname() + "was created");
    }

    public void addSeller(){
    
    }
    public void deleteSeller(){
    
    }
    public void createProgram(){
    
    }
}
