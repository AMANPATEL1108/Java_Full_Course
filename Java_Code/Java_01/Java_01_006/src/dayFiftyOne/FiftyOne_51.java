package dayFiftyOne;

import java.util.concurrent.*;

public class FiftyOne_51 {
    public static void main(String[] args) {

    }
}
//-----------------------------------barier------------------------------
//
//public class FiftyOne_51 {
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        int numberOfServices = 3;
//
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        CyclicBarrier barrier = new CyclicBarrier(numberOfServices);
//
//        executorService.submit(new DependentService(barrier));
//        executorService.submit(new DependentService(barrier));
//        executorService.submit(new DependentService(barrier));
//        System.out.println("Main");
//
//        barrier.reset();
//
////        executorService.shutdown(); //shutDown Now Use To Off Service only print Main if Time Out Then
//
//    }
//}
//
//class DependentService implements Callable<String> {
//
//    private final CyclicBarrier barrier;
//
//    public DependentService(CyclicBarrier barrier) {
//        this.barrier = barrier;
//    }
//
//    @Override
//    public String call() throws Exception {
//        System.out.println(Thread.currentThread().getName() + " Service Started");
//        Thread.sleep(1000);
//        System.out.println(Thread.currentThread().getName() + " is waiting at the barrier.");
//        barrier.await();
//        return "OK";
//    }
//}

//-------------------------------------Count Down------------------------------
//public class FiftyOne_51 {
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        int numberOfServices = 3;
//
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        CountDownLatch latch = new CountDownLatch(numberOfServices);
//
//        for (int i = 0; i < numberOfServices; i++) {
//            new Thread(new DependentService((latch))).start();
//        }
/// /        executorService.submit(new DependentService(latch));
/// /        executorService.submit(new DependentService(latch));
/// /        executorService.submit(new DependentService(latch));
//        latch.await(5, TimeUnit.SECONDS);
//        System.out.println("Main");
//        executorService.shutdown(); //shutDown Now Use To Off Service only print Main if Time Out Then
//
/// /        Future<String> future1 = executorService.submit(new DependentService());
/// /        Future<String> future2 = executorService.submit(new DependentService());
/// /        Future<String> future3 = executorService.submit(new DependentService());
/// /
/// /        future1.get();
/// /        future2.get();
/// /        future3.get();
/// /
/// /        System.out.println("All Depended Service Finished. Starting main Service....");
/// /        executorService.shutdown();
//    }
//}
//
//class DependentService implements Runnable {
//
//    private final CountDownLatch latch;
//
//    public DependentService(CountDownLatch latch) {
//        this.latch = latch;
//    }
//
//    @Override
//    public void run() {
//        try {
//            Thread.sleep(4000);
//            System.out.println(Thread.currentThread().getName() + " Service Started");
//        } catch (Exception e) {
//
//        } finally {
//            latch.countDown();
//
//        }
//    }
//}
