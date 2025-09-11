public class Day_Three {


    public static void main(String[] args) {
        greet();  // calling the method
        System.out.println(add(2, 3));
        System.out.println(add(2.5, 3.5));
        System.out.println(add(5,6,7));
        System.out.println(factorial(5));
        System.out.println(calSquare(10));
        System.out.println(checkEvenorOdd(5));
        System.out.println(findMax(5,15,10));
        System.out.println(convertCelsius(5));
        printInfo("aman");
        System.out.println(findSquareArea(25));
        System.out.println(findRectangleArea(10,20));
        System.out.println(findAreaofCircle(5.5));
        System.out.println(findFibonacci(5));
        System.out.println(sumOfDigit(12));
        System.out.println(calculatePower(2,3));
        System.out.println(reverseNumber(112,0));
    }

    public static int reverseNumber(int num, int rev) {
        if (num == 0) {
            return rev;
        }
        rev = rev * 10 + num % 10;
        return reverseNumber(num / 10, rev);
    }

    public static int calculatePower(int a,int b){
        if (b==0){
            return 1;
        }
        return a*calculatePower(a,b-1);
    }

        public static  int sumOfDigit(int a){
            if (a == 0) {
                return 0;
            }
            return (a % 10) + sumOfDigit(a / 10);
        }

    public static  int findFibonacci(int a){
        if (a<=1){
            return a;
        }
        return findFibonacci(a-1)+findFibonacci(a-2);
    }

    public static double findAreaofCircle(double a){
        return 3.14*(a*a);
    }

    public  static  int findSquareArea(int a){
        return a*a;
    }

    public static  int findRectangleArea(int l,int b){
        return l*b;
    }


    public static void printInfo(String name){
        System.out.println(name);
    }

    public static void printInfo(String name,int age){
        System.out.println(name);
        System.out.println(age);
    }
    public static void printInfo(String name,int age,String city){
        System.out.println(name);
        System.out.println(age);
        System.out.println(city);
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static int add(int a,int b){
        return a+b;
    }
    public static int add(int a,int b,int c){
        return a+b+c;
    }

    public static  int convertCelsius(int a){
        return (a * 9/5) + 32;
    }
    public static  int findMax(int a,int b,int c){
        if (a>b && a>c){
            return c;
        } else if (b>a && b>c) {
            return b;
        }
        return c;
    }



    public static boolean checkEvenorOdd(int num){
        return num % 2 == 0;
    }

    public static Integer calSquare(int a){
        return a*a;
    }

    public static int factorial(int n) {
        if (n == 0)  // base case
            return 1;
        else
            return n * factorial(n - 1);
    }

    public static void greet() {
        System.out.println("Hello!");
    }





}
