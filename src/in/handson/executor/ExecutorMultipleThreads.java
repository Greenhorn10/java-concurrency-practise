package in.handson.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorMultipleThreads {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit( () ->{
            System.out.println("Executing Thread 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed Thread 1");
        });
        executorService.submit( () ->{
            System.out.println("Executing Thread 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed Thread 2");
        });
        executorService.submit( () ->{
            System.out.println("Executing Thread 3");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed Thread 3");
        });

        executorService.shutdown();
    }
}
