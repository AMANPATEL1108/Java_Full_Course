public class Day_Seven {
//    Encapsulation + Access Modifiers
    public static void main(String[] args) {
//        Student s=new Student();
//        s.setAge(-12);
//        s.setName("aman");
//        System.out.println(s.getAge());
//        System.out.println(s.getName());

//        Book book=new Book("Abc");
//        System.out.println( book.getIsbn());

//        Employee employee=new Employee();
//        employee.setSalary(5000);
//        System.out.println(employee.getSalary());

//        Vehicle vehicle=new Car();
//        vehicle.setSpeed(500);
//        System.out.println(vehicle.getSpeed());
//
//        Person person=new Person("ap",12);
//        System.out.println(person.getName() +"-"+person.getAge());
//
//        Product product=new Product();
//        product.setName("k");
//        product.setPrice(-12);
//        product.setQuantity(-1);
//        System.out.println(product.getName());
//        System.out.println(product.getPrice());
//        System.out.println(product.getQuantity());

    }
}

//public class Laptop {
//    private String model;  //In this code getModel change to a return type here get Model is print not a return then here access all model of all users
//    public void getModel() {
//        System.out.println(model);
//    }
//}


//class Product{
//    private String name;
//    private double price;
//    private int quantity;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        if (!name.isEmpty()){
//            this.name = name;
//        }
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        if (price>0){
//            this.price = price;
//        }
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        if (quantity>=0){
//            this.quantity = quantity;
//        }
//    }
//}

//public class BankAccount {
//    public double balance; //her in this field balance is a public here this is can access anything
//
//    public void deposit(double amount) {
//        balance += amount;
//    }
//}


//class Person{
//    final  String name;
//    final int age;
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//}

//class Vehicle{
//    protected int speed;
//
//    public int getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(int speed) {
//        this.speed = speed;
//    }
//}
//
//class Car extends Vehicle{
//    public void setSpeed(int speed) {
//        this.speed = speed;
//    }
//
//}

//class Employee{
//    private double salary;
//
//    public double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(double salary) {
//        if (salary>10000){
//            this.salary = salary;
//        }
//    }
//}


//class User{
//    private String password; //In this case not access a Password Because here password is Private Field and not a getter Method then can not access in another class
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}

//class Book{
//    private String isbn;
//
//    public Book(String isbn) {
//        this.isbn = isbn;
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//}
//class Student{
//    private String name;
//    private  int age;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        if (age>=0){
//            this.age = age;
//        }
//    }
//}