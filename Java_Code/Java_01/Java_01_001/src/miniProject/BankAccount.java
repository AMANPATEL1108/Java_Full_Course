package miniProject;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transection{
    Integer amount;

    public Transection() {
    }

    String operation;

    public Transection(Integer amount, String operation) {
        this.amount = amount;
        this.operation = operation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}

class Account{
    private Integer number;
    private String name;
    private Integer balance;
    private Transection transections;

    public Transection getTransections() {
        return transections;
    }

    public void setTransections(Transection transections) {
        this.transections = transections;
    }

    public Account() {
    }

    public Account(Integer number, String name, Integer balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}


public class BankAccount {
    private static List<Account> accountList = new ArrayList<>();

    public static void main(String[] args) {
    welcomeBank();
    }

    public static void welcomeBank(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome To Bank");
        System.out.println("For account Create Press 1");
        System.out.println("For account Credit Press 2");
        System.out.println("For account Debit Press 3");
        System.out.println("For account Transfer Press 4");
        System.out.println("For Get Information  Press 5");
        System.out.println("*************************************************");

        Integer operation=sc.nextInt();

        switch (operation) {
            case 1:
                createAccount();
                break;
            case 2:
                credited();
                break;
            case 3:
                deposit();
                break;
            case 4:
                transfer();
                break;
            case 5:
                getInformation();
                break;
            default:
        }

    }

    private static void getInformation() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your Account Number:");
        Integer scn=sc.nextInt();
        List<Account> getAll=accountList;
        for(int i=0;i<accountList.size();i++){
            if (getAll.get(i).getNumber().equals(scn)){
                System.out.println("Your details is Number:"+getAll.get(i).getNumber());
                System.out.println("Your details is Name:"+getAll.get(i).getName());
                System.out.println("Your details is Balance:"+getAll.get(i).getBalance());
                System.out.println("Your details is transection:"+getAll.get(i).getTransections().getOperation()+"-"+getAll.get(i).getTransections().getAmount());
                break;
            }
        }
        welcomeBank();
    }

    private static void transfer() {
        List<Account> getAll = accountList;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Account Number:");
        Integer acNumber = sc.nextInt();
        for (int i = 0; i < accountList.size(); i++) {
            if (getAll.get(i).getNumber().equals(acNumber)) {
                System.out.println("Enter a Transfer Accounter Number");
                Integer tac = sc.nextInt();
                for (int j = 0; j < accountList.size(); j++) {
                    if (getAll.get(j).getNumber().equals(tac)) {
                        if (getAll.get(i).getNumber().equals(getAll.get(j).getNumber())) {
                            System.out.println("This is Same Account");
                            break;
                        } else {
                            System.out.println("Enter amount to Transfer:");
                            Integer t = sc.nextInt();
                            Integer c = getAll.get(i).getBalance();
                            if (c < t) {
                                System.out.println("Less Amount Found");
                                break;
                            } else {
                                getAll.get(i).setBalance(getAll.get(i).getBalance() - t);
                                Transection tran=new Transection();
                                tran.setAmount(t);
                                tran.setOperation("Debited");
                                getAll.get(i).setTransections(tran);
                                System.out.println("Now Your Amount is " + (getAll.get(i).getBalance() - c));
                                getAll.get(j).setBalance(getAll.get(j).getBalance() + t);
                                Transection tran2=new Transection();
                                tran2.setAmount(t);
                                tran2.setOperation("Credited");
                                getAll.get(j).setTransections(tran2);

                                System.out.println("Amount Transfer SuccessFully");
                                break;
                            }
                        }
                    }
                }
            }else {
                System.out.println("User Not Found of Owner");
            }
        }

        welcomeBank();

    }

    public static void createAccount(){
        Account account=new Account();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your Name:");
        String userName=sc.nextLine();
        System.out.println("Enter AccountNumber:");
        Integer accountNumber=sc.nextInt();
        System.out.println("Enter Amount:");
        Integer balance=sc.nextInt();
        System.out.println("*************************************");
        account.setName(userName);
        account.setNumber(accountNumber);
        account.setBalance(balance);
        accountList.add(account);
        System.out.println("Your Account Created "+ userName +"With "+accountNumber+" this and Balance is"+balance);
        welcomeBank();
    }

    public static void deposit(){
        List<Account> getAll=accountList;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your Account Number:");
        Integer acNumber=sc.nextInt();
        for(int i=0;i<accountList.size();i++)
            if (getAll.get(i).getNumber().equals(acNumber)){
                System.out.println("Enter Debited Amount");
                Integer acAmount=sc.nextInt();
                Integer currentAmount= getAll.get(i).getBalance();
                getAll.get(i).setBalance(currentAmount-acAmount);
                System.out.println("Now Your Amount is"+(currentAmount-acAmount));
                Transection tran=new Transection();
                tran.setAmount(acAmount);
                tran.setOperation("Debited");
                getAll.get(i).setTransections(tran);
                welcomeBank();
            }else{
                System.out.println("USer Not Found");
                welcomeBank();
            }

    }

    public static void credited(){
        List<Account> getAll=accountList;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your Account Number:");
        Integer acNumber=sc.nextInt();
       for(int i=0;i<accountList.size();i++)
        if (getAll.get(i).getNumber().equals(acNumber)){
            System.out.println("Enter Credited Amount");
            Integer acAmount=sc.nextInt();
            Integer currentAmount= getAll.get(i).getBalance();
            getAll.get(i).setBalance(currentAmount+acAmount);
            Transection tran=new Transection();
            tran.setAmount(acAmount);
            tran.setOperation("credited");
            getAll.get(i).setTransections(tran);
            System.out.println("Now Your Amount is"+(currentAmount+acAmount));
            welcomeBank();

       }

    }



}


