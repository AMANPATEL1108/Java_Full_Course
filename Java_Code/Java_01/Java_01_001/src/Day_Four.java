public class Day_Four {
    public static void main(String[] args) {

        //Answer 1
//        Student student=new Student("dev",12,'A');
//        student.displayDetails();

        //Answer 2
//        BankAccount bankAccount=new BankAccount(1256,500);
//        bankAccount.printDetails();
//        bankAccount.deposit(50);
//        bankAccount.printDetails();
//        bankAccount.withdraw(100);
//        bankAccount.printDetails();

        //Answer 3
        Rectangle rectangle=new Rectangle(10,5);
        Rectangle rectangle2=new Rectangle(20,5);


    }

}

class Rectangle{
    Integer length;
    Integer breadth;

    public Rectangle(Integer length, Integer breadth) {
        this.length = length;
        this.breadth = breadth;
        calculateArea(length,breadth);
    }

    public void calculateArea(Integer length,Integer breadth){
        System.out.println("Area of"+length+"-"+breadth+"is:"+length*breadth);

    }
}


class BankAccount{
    Integer accountNumber;
    Integer balance;

    public BankAccount(Integer accountNumber, Integer balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount){
        this.balance= (int) (balance-amount);

    }

    public void withdraw(double amount){
        this.balance=(int) (balance-amount);
    }

    public  void printDetails(){
        System.out.println("Account Number-"+this.accountNumber+"- Balance"+this.balance);
    }
}

//class Student{
//    String name;
//    Integer rollNo;
//    char grade;
//
//    public Student(String name, Integer rollNo, char grade) {
//        this.name = name;
//        this.rollNo = rollNo;
//        this.grade = grade;
//    }
//
//    public void  displayDetails(){
//        System.out.println(this.name+"-"+this.rollNo+"-"+this.grade);
//    }
//}
