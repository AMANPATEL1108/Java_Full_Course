public class Day_Five {

    public static void main(String[] args) {
//        Car car=new Car();
//        car.start();
//        Manager manager=new Manager();
//        Device d1=new Mobile();
//        Device d2=new Laptop();
//        d1.showDetails();
//        d2.showDetails();
//        SBI sb=new SBI();
//        sb.displayRate();
//        Child child=new Child();
//        Bus bus = new Bus();
//        bus.display();
//        Ebook e = new Ebook("Java Mastery", "PDF");

        SuperAdmin obj = new SuperAdmin();
        System.out.println("Name from Admin: " + obj.name);      // "Admin"
        System.out.println("Name from User: " + ((User)obj).name); // "User"
        obj.info();

    }
}

class User{
    String name = "User";
    User(){
        System.out.println("User constructor");
    }
    void info(){
        System.out.println("User Info");
    }
}

class Admin extends User{
    String name = "Admin";

    Admin(){
        super();
        System.out.println("Admin Constructor");
    }
    void info(){
        super.info();
        System.out.println("Admin Info");
    }
}

class SuperAdmin extends Admin{
    SuperAdmin(){
        super();
        System.out.println("SuperAdmin constructor");
    }
}

class Book{
    Book(String title){
        System.out.println("Book "+title);
    }
}

class Ebook extends Book{
    Ebook(String title, String format){
        super(title);
        System.out.println("Formate "+format);
    }
}

class Transport {
    int capacity = 100;

    Transport() {
    }

    void display() {
        System.out.println("Transport capacity:" + capacity);
    }
}

class Bus extends Transport {
    int capacity = 60;

    void display() {
        super.display();
        System.out.println("Bus capacity:" + capacity);
    }
}

class Grandparent {
    Grandparent() {
        System.out.println("Grandparent constructor");
    }
}

class Parent extends Grandparent {
    Parent() {
        super();
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    Child() {
        super();
        System.out.println("Child constructor");
    }
}

class Bank {
    Integer rate = 5;

    void displayRate() {
        System.out.println("Bank Rate" + rate);
    }

}

class SBI extends Bank {
    Integer rate = 7;

    void displayRate() {
        System.out.println("SBI Rate" + rate);
        super.displayRate();
    }
}


class Device {
    void showDetails() {
        System.out.println("Showing Details of Device");
    }
}

class Mobile extends Device {
    void showDetails() {
        super.showDetails();
        System.out.println("Showing a Mobile Details");
    }
}

class Laptop extends Device {
    void showDetails() {
        super.showDetails();
        System.out.println("Showing a Laptop Details");
    }
}


class Employee {
    Integer salary = 50000;

    Employee() {
        System.out.println("Employee Salary is " + salary);
    }
}

class Manager extends Employee {
    Integer bonus = 10000;

    Manager() {
        super();
        System.out.println("bonus is " + bonus);
    }
}


class Car extends Vehicle {
    void start() {
        super.start();
        System.out.println("Car started");
    }

}

class Vehicle {

    void start() {
        System.out.println("Vehicle is stared");
    }
}