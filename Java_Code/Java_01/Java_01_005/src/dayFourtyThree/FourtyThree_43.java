package dayFourtyThree;


class MyThreadRunnable1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("I am Thread not a threat 1 of position " + i);
        }
    }
}

class MyThreadRunnable2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("I am Thread not a threat 2 of position " + i);
        }
    }
}

public class FourtyThree_43 {
    public static void main(String[] args) {

        MyThreadRunnable1 bullet1 = new MyThreadRunnable1();
        Thread gun1 = new Thread(bullet1);
        MyThreadRunnable2 bullet2 = new MyThreadRunnable2();
        Thread gun2 = new Thread(bullet2);
        gun1.start();
        gun2.start();

    }
}

//Work Like This of a Thread Life Cycle

//New -> Runnable -> Running -> Non Runnable -> Terminate
