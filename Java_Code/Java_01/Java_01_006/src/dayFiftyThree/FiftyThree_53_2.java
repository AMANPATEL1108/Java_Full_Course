package dayFiftyThree;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class FiftyThree_53_2 {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(5);

        Thread prducerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
//                    queue.add(i);
                    queue.put(i);
                    System.out.println("Produce: "+i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
//                    queue.poll();
                    Integer value=queue.take();
                    System.out.println("Consumed: "+value);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        prducerThread.start();
        consumerThread.start();
    }

}
