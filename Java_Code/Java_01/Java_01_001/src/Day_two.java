import java.util.Arrays;

public class Day_two {
    public static void main(String[] args) {

//        primitive Dt
//        int a = 10;	              Integer numbers
//        double pi = 3.14;	          Decimal numbers
//        char letter = 'A';	      Single character
//        boolean isTrue = true;      true or false values
//        byte, short, long, float

        int a =10;
        double d=2.5;
        boolean isTrue=true;
        char letter='A';
        Long num= 1000000000000L;
        short strt=10;
        float flt=1.5F;
        byte bt=10;


//        Non-Primitive (Reference) Data Types

//        String, Arrays, Objects, etc.
        String name="aman";
        int[] arr=new int[5];
        Object obj="aman";


        int number = 10;

        if (number > 0) {
            System.out.println("Positive number");
        } else if (number < 0) {
            System.out.println("Negative number");
        } else {
            System.out.println("Zero");
        }

        //For Loops
        for (int i = 1; i <= 5; i++) {
            System.out.println("Count: " + i);
        }

        //While Loop
        int i = 1;
        while (i <= 5) {
            System.out.println("While count: " + i);
            i++;
        }

        //do while Loop
        int j = 1;
        do {
            System.out.println("Do-While count: " + j);
            j++;
        } while (j <= 5);

        //For Each Loops
        int[] numberss = {10, 20, 30, 40, 50};

        for (int nume : numberss) {
            System.out.println("Number: " + nume);
        }


        //Switch Case
        int day = 3;

        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Other day");
        }



    }
}
