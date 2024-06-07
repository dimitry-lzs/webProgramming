package com.webProgramming.bonus;
import com.webProgramming.models.*;


public class CreateUsers {
    public static void main(String[] args) {
        Admin admin = new Admin("the man", "the myth", "the legend");

        admin.register();
        admin.login();

        Seller seller = admin.createSeller("giorgio", "Agent", "Bukovski");

        seller.register();
        seller.login();

        //Test classes
        Program program = admin.createProgram("Student's", 15, 50, 12);
        program.printProgram();

        PhoneNumber phonenumber = new PhoneNumber("1234567890", program);
        phonenumber.printPhoneNumber();

        PhoneNumber phone2 = new PhoneNumber("0987654321", program);
        phone2.printPhoneNumber();

        //Create Client through Seller.
        Client client = seller.addClient("424", 2342, "crazyback", "Duden", "Dudenski");
        Client client2 = seller.addClient("425", 234, "crazyback2", "Vlad", "Uzumaki");

        client.register();
        client.login();
        client2.register();
        client2.login();

        System.out.println("Total users: " + User.getCount()); // 3

        Call call1 = phonenumber.call(phone2, 2);
        call1.printCall();

        Call call2 = phone2.call(phonenumber, 10);
        call2.printCall();

        Call[] clientCalls = {call1};

        Bill bill = seller.issueBill(phonenumber, clientCalls);
        bill.printBill();

        Call[] client2Calls = {call2};
        client.setBills(new Bill[]{bill});

        client.viewBills();
        Bill bill2 = seller.issueBill(phone2, client2Calls);
        bill2.printBill();

        client2.setBills(new Bill[]{bill2});

        seller.changeClientProgram();

        seller.logout();

        admin.deleteSeller(seller.getUsername());
        admin.deleteProgram(program.getProgramName());

        admin.logout();
        client.logout();
    }
}
