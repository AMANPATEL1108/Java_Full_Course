public class Day_Eight {
    public static void main(String[] args) {

//        Manager m=new Manager();

//        Animal animal=new Animal() {
//            @Override
//            public void sound() {
//                System.out.println("called Animal From main");
//            }
//        };
//
//        Animal dog=new Dog();
//        dog.sleep();

//        Game football=new Football();
//        football.playGame();
//        Game cricket=new Cricket();
//        cricket.playGame();
    }
}

//✅ Bonus: Quick Theoretical MCQs (Ask Yourself)
//
//Can we declare constructors inside interfaces?-NO We Can Not Declare Constructor in Interface
//
//What happens if we don’t override all abstract methods in a subclass? - Nothing but if We Want to use that then is not implement in a abstract class then we can implement in a subclass
//
//Can an interface extend another interface?-Yes but we can Implement that method in that if that is a interface another
//
//Can abstract classes have final methods?-yes but after we can not Change that Implementation in Another that Subclass
//
//Can we have static methods in interfaces?-No Interface not allow a static method in that

interface Logger{
   void log(String message);
}

class FileLogger implements  Logger{

    @Override
    public void log(String message) {
        System.out.println("Writing to file:"+message);

    }
}

class ConsoleLogger implements Logger{

    @Override
    public void log(String message) {
        System.out.println("Console:"+message);
    }
}

class LogManager{
   Logger consoleLogger=new ConsoleLogger();
   Logger fileLogger=new FileLogger();

    public Logger getConsoleLogger() {
        return consoleLogger;
    }

    public Logger getFileLogger() {
        return fileLogger;
    }
}

abstract  class  Notification{
    abstract void send(String message);
}

class EmailNotification extends Notification{
    @Override
    void send(String message) {
        System.out.println("Called a Email Notification");
    }
}
class SMSNotification extends Notification{
    @Override
    void send(String message) {
        System.out.println("Called a SMS Notification ");
    }
}
class PushNotification extends Notification{
    @Override
    void send(String message) {
        System.out.println("Called a Push Notification");
    }
}

abstract class Game{

    void playGame(){
        start();
        end();
        play();
    }
     void start(){
        System.out.println("Called a Start");
    }
    void end(){
        System.out.println("Called end");
    }

    void play(){
        System.out.println("Called Play");
    }
}

class Cricket extends Game{

    void play(){
        System.out.println("Called Play From Cricket");
    }
}

class Football extends  Game{
    void play(){
        System.out.println("Called Play From Football");
    }
}


interface Payment{
    void pay(double amount);
}
class CreditCardPayment implements Payment{

    void processPayment(Payment p){
        pay(56);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Called From CreditCard:"+amount);
    }
}
class PayPalPayment implements Payment{

    void processPayment(Payment p){
        pay(12);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Called From PayPalPayment:"+amount);
    }
}

interface A {
    default void show() { System.out.println("A"); }
}

interface B {
    default void show() { System.out.println("B"); }
}

class MyClass implements B,A  {
    @Override
    public void show() {
        A.super.show();
    }
    // How do you resolve the conflict?-Adding that Class name before Super
}



interface Animal{
    void sound();
    default void sleep(){
        System.out.println("Sleeping Animal ....");
    }
}
class Dog implements Animal{

    @Override
    public void sound() {
        System.out.println("Called Override Sound of Animal from Dog");
    }


}



abstract  class Employee{
    Employee(){
        System.out.println("Employee Created");
    }
}

class Manager extends Employee{
    Manager(){
        super();
    }
}

interface Engine {
    void start();
}

interface BrakeSystem {
    void applyBrakes();
}

class Car implements  BrakeSystem,Engine{

    @Override
    public void applyBrakes() {
        System.out.println("Called Apply Break From Car");
    }

    @Override
    public void start() {
        System.out.println("Called Start From Car");

    }
}


interface Drawable{
    void draw();
}

class Circle implements  Drawable{

    @Override
    public void draw() {
        System.out.println("Called Draw From Circle");
    }
}
class Rectangle implements  Drawable{

    @Override
    public void draw() {
        System.out.println("Called Draw From Rectangle");
    }
}
//
//abstract class Vehicle{
//        abstract void move();
//
//        void fuelType(){
//            System.out.println("Fuel Called");
//        }
//}
//
//class  Car extends Vehicle{
//    void move(){
//        System.out.println("Moved Called in a Bike");
//    }
//}
//
//class Bike extends Vehicle{
//    void move(){
//        System.out.println("Moved Called in a Bike");
//    }
//}
