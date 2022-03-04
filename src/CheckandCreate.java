import java.util.*;

public  class CheckandCreate {
    protected ArrayList<Account> accounts = new ArrayList<>();
    protected static Map<Integer, ArrayList<Integer>> ssnAccType = new HashMap<>();
    private  int ssn;
    private  int accountType;
    private String name;
    private Date dob;
    private int phone;
    private static final int savingslimit = 3;
    protected static Map<Integer, Integer> savingsWithdraw = new HashMap<>();



    public CheckandCreate(int ssn, int accountType) {
        this.ssn = ssn;
        this.accountType = accountType;
    }

    public  void getInfo(String name, Date dob, int phone){
        this.name = name;
        this.dob = dob;
        this.phone = phone;
    }

    public static void removeAccount(int accountnumber, int ssn, int accountType) {
        System.out.println("SSN "+ssn);
        if (!ssnAccType.containsKey(ssn)) {
            System.out.println("No account associated with this ssn");
            return;
        }
        else{
            ArrayList<Integer> accounttypes = ssnAccType.get(ssn);
            if (accounttypes.contains(accountType)) {
                accounttypes.remove(Integer.valueOf(accountType));
                if (accounttypes.size() == 0) {
                    ssnAccType.remove(ssn);
                }
            }
        }

        if(Account.account_information.containsKey(accountnumber)){
            Integer[] accountinfo = Account.account_information.get(accountnumber);
            if(accountinfo[0]==accountType){
                Account.account_information.remove(accountnumber);
                System.out.println("Account Removed");
            }
            else {
                System.out.println("Account of this type doesnt exists");
            }
        }
        else {
            System.out.println("Account Number doesn't exist");
        }
    }

    public void maintainsInfo(){
            ArrayList<Integer> accounttypes =  ssnAccType.get(this.ssn);
            if(accounttypes==null){
                accounttypes = new ArrayList<Integer>();
                accounttypes.add(this.accountType);
                ssnAccType.put(this.ssn, accounttypes);
            }
            else {
               if(!accounttypes.contains(this.accountType)) accounttypes.add(this.accountType);
            }
    }

    public void createAccount(){
        maintainsInfo();
        Account acc = new Account(this.name, this.dob, this.ssn, this.phone, this.accountType);
        int accnum  = acc.addAccount();
        savingsWithdraw.put(accnum, 1);
        accounts.add(acc);
        System.out.println("Account created successfully, Your account number is "+acc.getAccountNum());
        printssninfo();
    }

    public boolean checkAccount(){
        for (Map.Entry<Integer, ArrayList<Integer>> ssa: ssnAccType.entrySet()){
            int ssnkey = ssa.getKey();
            List<Integer> atype = ssa.getValue();

            if(ssnkey == this.ssn){
                for(int i:atype){
                    if(i == this.accountType){
                        System.out.println("Account already exists!");
                        return false;
                    }
                }
            }
        }
        return true;
        }

    public static void checkBalance(int accountNumber){
        Integer[] info = Account.account_information.get(accountNumber);
        System.out.println(info[1]);
    }

    public static void addBalance(int accountNumber, int amount){
        Integer[] info = Account.account_information.get(accountNumber);
        info[1] = info[1] + amount;
        System.out.println("Money deposited");
    }

    public static void withdraw(int accountNumber, int amount){


        Integer[] info = Account.account_information.get(accountNumber);
        int accountType = info[0];

        if(accountType==2 && savingsWithdraw.get(accountNumber) > savingslimit){
            System.out.println(" Sorry, you have maxed out your withdrawal limit for this checking account");
        }

        else if(accountType==2 && savingsWithdraw.get(accountNumber) <= savingslimit){
            if(amount > info[1]){
                System.out.println("Insufficient funds");
            }
            else {
                info[1] = info[1] - amount;
                System.out.println("Please collect your cash");
//            int limit   = savingsWithdraw.get(accountNumber) ;
                savingsWithdraw.replace(accountNumber, savingsWithdraw.get(accountNumber)+1);
//                System.out.println(savingsWithdraw.get(accountNumber)-1);
            }

        }
        else{
            if(amount > info[1]){
                System.out.println("Insufficient funds");
                return;
            }
            info[1] = info[1] - amount;
            System.out.println("Please collect your cash");
        }

    }

    static boolean  checkAccountNumber(int accountNumber){
        return Account.account_information.containsKey(accountNumber);
    }

    void printssninfo(){
        System.out.println("here");
        for (Map.Entry<Integer, ArrayList<Integer>> ssa: ssnAccType.entrySet()){
            int ssnkey = ssa.getKey();
            List<Integer> atype = ssa.getValue();
                for(int i:atype){
                    System.out.println("SSN KEY " + ssnkey + " account type " +i);
                    }
                }
        }
}


