//public class Day_Six {
//    public static void main(String[] args) {
//        // Calculator calculator=new Calculator();
//        // calculator.add(12,15);
//        // Animal a = new Dog();
//        // a.makeSound();
//        // Test t = new Test();
//        // t.show(10, 20); // In this can Not Find a Method of that have Both Parameter is Integer then Error Showing
//
//        Shape[] shapes = new Shape[3];
//
//        // Assign Circle and Square objects to the array
//        shapes[0] = new Circle();
//        shapes[1] = new Square();
//        shapes[2] = new Shape();
//
//        for (Shape shape : shapes) {
//            shape.draw();  // Runtime decides which draw() to call
//        }
//    }
//
//    interface Drawable {
//        void draw();
//    }
//
//    class Circle implements Drawable {
//        @Override
//        public void draw() {
//            System.out.println("From Circle");
//        }
//    }
//
//    class Rectangle implements Drawable {
//        @Override
//        public void draw() {
//            System.out.println("From Rectangle");
//        }
//    }
//
//    class Triangle implements Drawable {
//        @Override
//        public void draw() {
//            System.out.println("From Triangle");
//        }
//    }
//
//    // 9. Final & Private Methods
//    // True or False:
//    // private methods can be overridden-No
//    // class P{
//    //   private void getP(){
//    //        System.out.println("Called From P");
//    //    }
//    // }
//    // class C extends P{
//    //    private void getP(){
//    //        System.out.println("Called From C");
//    //    }
//    // }
//
//    // final methods can be overridden-No Because here after final we can not change that-Showing Error
//
//    // class A{
//    //    final void hello(){
//    //        System.out.println("From A Called Hello");
//    //    }
//    // }
//    //
//    // class B extends  A{
//    //    // This will cause compile-time error, so commenting out
//    //    // final void hello(){
//    //
//    //    // }
//    // }
//
//    // static methods can be overridden-Yes
//
//    // class F{
//    //    static void hell(){
//    //        System.out.println("Go Hell F");
//    //    }
//    // }
//    //
//    // class G extends F{
//    //    static void hell(){
//    //        System.out.println("Go Hell G");
//    //    }
//    // }
//
//    // Explain with code examples.
//
//    // =====================
//
//    class Shape {
//        void draw() {
//            System.out.println("Drawing Shape");
//        }
//    }
//
//    class Circle extends Shape {
//        @Override
//        void draw() {
//            System.out.println("Drawing Circle");
//        }
//    }
//
//    class Square extends Shape {
//        @Override
//        void draw() {
//            System.out.println("Drawing Square");
//        }
//    }
//
//    // =====================
//
//    // class Parent {
//    //     static void greet() {
//    //         System.out.println("Hello from Parent");
//    //     }
//    // }
//    //
//    // class Child extends Parent {
//    //     static void greet() {
//    //         System.out.println("Hello from Child");
//    //     }
//    // }
//    //
//    // public class Test {
//    //     public static void main(String[] args) {
//    //         Parent obj = new Child();
//    //         obj.greet();  // Output? Output is :Hello Child
//    //     }
//    // }
//
//    // class Vehicle{
//    //     Vehicle getType(){
//    //         return new Vehicle();  // added 'new' keyword here
//    //     }
//    // }
//    //
//    // class Car extends Vehicle{
//    //     @Override
//    //     Car getType(){
//    //         return new Car();  // added 'new' keyword here
//    //     }
//    // }
//
//    // class Parent {
//    //     void display(){
//    //         System.out.println("Called Parent");
//    //     }
//    // }
//    //
//    // class Child extends Parent{
//    //     @Override
//    //     void display(){
//    //         super.display();
//    //         System.out.println("Called Child");
//    //     }
//    // }
//
//    class Test {
//        // In this can Not Find a Method of that have Both Parameter is Integer then Error Showing
//        void show(int a, double b) {
//            System.out.println("int-double");
//        }
//
//        void show(double a, int b) {
//            System.out.println("double-int");
//        }
//    }
//
//    class Animal {
//        void makeSound() {
//            System.out.println("Animal Make Sound");
//        }
//    }
//
//    class Dog extends Animal {
//        @Override
//        void makeSound() {
//            System.out.println("Dog Make Sound");
//        }
//    }
//
//    class Cat extends Animal {
//        @Override
//        void makeSound() {
//            System.out.println("Cat Make Sound");
//        }
//    }
//
//    class Cow extends Animal {
//        @Override
//        void makeSound() {
//            System.out.println("Cow Make Sound");
//        }
//    }
//
//    class AreaCalculator {
//        int area(int side) {
//            System.out.println(side * side);
//            return side * side;
//        }
//
//        int area(int length, int breadth) {
//            System.out.println(length * breadth);
//            return length * breadth;
//        }
//
//        double area(double radiu) {
//            System.out.println(3.14 * (radiu * radiu));
//            return 3.14 * (radiu * radiu);
//        }
//    }
//
//    class Calculator {
//        int add(int a, int b) {
//            System.out.println(a + b);
//            return a + b;
//        }
//
//        double add(double a, double b) {
//            System.out.println(a + b);
//            return a + b;
//        }
//
//        int add(int a, int b, int c) {
//            System.out.println(a + b + c);
//            return a + b + c;
//        }
//    }
//}
