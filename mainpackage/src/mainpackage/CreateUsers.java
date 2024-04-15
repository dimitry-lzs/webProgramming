package mainpackage;
import mainpackage.Classes.*;


public class CreateUsers {
    public static void main(String[] args) {
        Admin admin = new Admin("AdminUsername", "AdminName", "AdminSurname");
        System.out.println(admin.getType());
        Seller seller = new Seller("SellerUsername", "SellerName", "SellerSurname");
        System.out.println(seller.getType());

        //Test classes
        Program program = new Program("Test Program", 15, 50);
        program.printProgram();

        PhoneNumber phonenumber = new PhoneNumber("1234567890", program);
        phonenumber.printPhoneNumber();

        //Create Client through Seller.
        Client client = seller.addClient("ClientAFM", phonenumber, "ClientUsername", "ClientName", "ClientSurname");
        System.out.println(client.getType());

        System.out.println(User.getCount()); // 3

        Call call1 = new Call(22, program);
        call1.printCall();

        Call call2 = new Call(77, program);
        call2.printCall();

        Call[] callsthismonth = {call1, call2};

        Bill bill = new Bill(12, phonenumber, callsthismonth);
        bill.printBill();
    }
}
