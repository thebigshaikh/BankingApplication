import java.util.Date;
import java.util.Random;
import java.util.*;

public class Account {
    private String Name;
    private Date DoB;
    private int ssn;
    private int phone;
    private int account_type;
    private int[] accountNumbers= new int[100];
    private int accountNum;
    private static int count;
    private int balance;
    private int amount;
    static HashMap<Integer,Integer[]> account_information = new HashMap<Integer,Integer[] >();

    public Account(String name, Date doB, int ssn, int phone, int account_type) {
        this.Name = name;
        this.DoB = doB;
        this.ssn = ssn;
        this.phone = phone;
        this.account_type = account_type;
    }


    public int addAccount(){
        createAccountNumber();
        accountNumbers[count] = this.accountNum;
        account_information.put(this.accountNum,new Integer[]{this.account_type, this.balance});
        count += 1;
        return this.accountNum;
    }

    public void createAccountNumber(){
        Random rand = new Random();
        int num = rand.nextInt(this.ssn);
        this.accountNum = num + this.phone;
    }

    public int getAccountNum() {
        return accountNum;
    }
}
