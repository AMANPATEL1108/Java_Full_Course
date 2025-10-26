package dayFourtyNine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FourtyNine_49_2 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " completed by " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
