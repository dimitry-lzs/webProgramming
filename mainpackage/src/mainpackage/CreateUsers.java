package mainpackage;
import mainpackage.Classes.*;


public class CreateUsers {
    public static void main(String[] args) {
        Admin admin = new Admin();
        System.out.println(admin.getType());
        Seller seller = new Seller();
        System.out.println(seller.getType());
        Client client = new Client();
        System.out.println(client.getType());

        System.out.println(User.getCount()); // 3
    }
}
