package mainpackage;
import mainpackage.Classes.*;


public class CreateUsers {
    public static void main(String[] args) {
        Admin admin = new Admin("the man", "the myth", "the legend");

        admin.register();
        admin.login();

        Seller seller = admin.createSeller("georhj", "Agent", "Bukovski");

        seller.register();
        seller.login();

        //Test classes
        Program program = admin.createProgram("Student's", 15, 50, 12);
        program.printProgram();

        PhoneNumber phonenumber = new PhoneNumber("1234567890", program);
        phonenumber.printPhoneNumber();

        //Create Client through Seller.
        Client client = seller.addClient("p424", phonenumber, "crazyback", "Duden", "Dudenski");

        client.register();
        client.login();

        System.out.println("Total users: " + User.getCount()); // 3

        PhoneNumber phone2 = new PhoneNumber("0987654321", program);

        Call call1 = new Call(phone2, phonenumber, 1, 20);
        call1.printCall();

        Call call2 = new Call(phonenumber, phone2, 5, 10);
        call2.printCall();

        Call[] callsthismonth = {call1, call2};

        Bill bill = new Bill(12, phonenumber, callsthismonth);
        bill.printBill();

        admin.logout();
        seller.logout();
        client.logout();
    }
}
