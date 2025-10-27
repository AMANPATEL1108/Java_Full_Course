package dayFifty;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Fifty_50 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//       try {
          java.util.concurrent.CompletableFuture<String> f1= CompletableFuture.supplyAsync(() -> {
               try {
                   Thread.sleep(5000);
                   System.out.println("Worker");
               } catch (Exception e) {

               }
               return "OK";
           }).thenApply(x-> x + x );
        System.out.println(f1.get());
//       }catch (InterruptedException e){
//           throw new RuntimeException();
//       }catch (Exception e){
//           throw  new RuntimeException();
//       }

//        System.out.println("Main");






//        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(5000);
//                System.out.println("Worker");
//            } catch (Exception e) {
//
//            }
//            return "OK";
//        });
//
//
//        CompletableFuture<Void> f = CompletableFuture.allOf(f1, f2);
//        f.join();
//        System.out.println("Main");
    }
}
