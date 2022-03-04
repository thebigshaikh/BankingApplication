import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class BankApplication {

    public static void showOptions(Scanner sc) throws ParseException {
        System.out.println("Select the number from the following options");
        System.out.println("1. Open Account \n2. Close Account \n3. Deposit \n4. Withdraw \n5. Check Balance\n6. Quit");
        int option;
        if (sc.hasNextInt())
        {
            option = sc.nextInt();
        }
        else {
            System.out.println("Incorrect input given \n Try again");
            option = 0;
        }

        switch (option){
            case 1:
                Scanner s = new Scanner(System.in);
                System.out.println(" Which type of Account do you want to open?");
                System.out.println("1. Checkings\n 2. Savings");
                int accountType = s.nextInt();
                System.out.println(" Enter your SSN");
                int ssn = s.nextInt();
                CheckandCreate cc = new CheckandCreate(ssn, accountType);
                if (accountType == 1){
                    if (!cc.checkAccount()) {
                        System.out.println("Checking Account already exists!");

                    }
                    else {
                        cc.maintainsInfo();
                        System.out.println("Enter your name");
                        String name = s.next();
                        System.out.println("Enter your phone");
                        int phone = s.nextInt();
                        System.out.println("Enter your dob as MM-DD-YYYY");
                        Date dob=new SimpleDateFormat("MM-dd-yyyy").parse(s.next());
                        Account newAccount = new Account(name, dob, ssn, phone, accountType);
                        cc.getInfo(name, dob, phone);
                        cc.createAccount();
                    }

                }
                else if (accountType == 2){
                    if (!cc.checkAccount()) {
                        System.out.println("Saving Account already exists!");
                        break;
                    }
                    else {
                        System.out.println("Enter your name");
                        String name = s.next();
                        System.out.println("Enter your phone");
                        int phone = s.nextInt();
                        System.out.println("Enter your dob as MM-DD-YYYY");
                        try{
                            Date dob=new SimpleDateFormat("MM-dd-yyyy").parse(s.next());
                            cc.getInfo(name, dob, phone);
                        }
                        catch (ParseException ex){
                            System.out.println("Dob inserted in an incorrect format, Please try again");
                            Date dob=new SimpleDateFormat("MM-dd-yyyy").parse(s.next());
                            cc.getInfo(name, dob, phone);
                        }
                        cc.createAccount();

                    }

                }
                else {
                    System.out.println(" Invalid input");
                    break;
                }
                BankApplication.showOptions(sc);

                break;

            case 2:
                System.out.println("Enter account number");
                int AN = sc.nextInt();
                System.out.println("Enter SSN");
                int ssnumber = sc.nextInt();
                System.out.println(" What is your account type?");
                System.out.println("1.Checking Account\n2.Saving Account");
                int acctype = sc.nextInt();
                CheckandCreate.removeAccount(AN,ssnumber,acctype);
                BankApplication.showOptions(sc);
                break;

            case 3:
                System.out.println(" Enter account number ");
                int an = sc.nextInt();
                if (!CheckandCreate.checkAccountNumber(an)){
                    System.out.println("Incorrect Account Number or Account doesn't exists!");
//                    return;
                }
                else {
                    System.out.println("Enter amount ");
                    int amount = sc.nextInt();
                    CheckandCreate.addBalance(an, amount);
                }
                BankApplication.showOptions(sc);
                break;

            case 4:
                System.out.println(" Enter account number ");
                int accountnumber = sc.nextInt();
                if (!CheckandCreate.checkAccountNumber(accountnumber)){
                    System.out.println("Incorrect Account Number or Account doesn't exists!");
                }
                else {
                    System.out.println("Enter amount ");
                    int am = sc.nextInt();
                    CheckandCreate.withdraw(accountnumber, am);
                }
                BankApplication.showOptions(sc);
                break;

            case 5:
//                open account
                System.out.println(" Enter account number ");
                int accountnum = sc.nextInt();
                if (!CheckandCreate.checkAccountNumber(accountnum)){
                    System.out.println("Incorrect Account Number or Account doesn't exists!");
                }
                else {
                    CheckandCreate.checkBalance(accountnum);
                }
                BankApplication.showOptions(sc);
                break;

            case 6:
                break;

            case 0:
                Scanner scnew = new Scanner(System.in);
                showOptions(scnew);
            default:
                System.out.println("Option not available");
                BankApplication.showOptions(sc);
        }
    }

    public static void main(String[] args) throws ParseException {
        System.out.println("Hello, What would you like to do today?");
        Scanner sc = new Scanner(System.in);
        BankApplication.showOptions(sc);

    }
}
