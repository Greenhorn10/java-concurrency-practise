package in.handson.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorMultipleCallable {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Callable<String> callable1 = () -> {
            Thread.sleep(1000);
            return "Callable 1 processed";
        };
        Callable<String> callable2 = () -> {
            Thread.sleep(2000);
            return "Callable 2 processed";
        };
        Callable<String> callable3 = () -> {
            Thread.sleep(2000);
            return "Callable 3 processed";
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(callable1, callable2, callable3));

        String invokeAny = executorService.invokeAny(Arrays.asList(callable1, callable2, callable3));

        System.out.println("Fastest processed Thread: "+invokeAny);

        for(Future<String> future: futures){
            System.out.println(future.get());
        }

        executorService.shutdown();

    }
}
