package in.handson.executor;

import java.util.concurrent.*;

public class ExecutorCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            Thread.sleep(2000);
            return "Returning value after processing task";
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(callable);

        System.out.println("Some other processing");

        while (!future.isDone()){
            Thread.sleep(200);
            System.out.println("Task still not done");
        }

        String result = future.get();
        System.out.println("Promised Result: "+result);

        executorService.shutdown();
    }
}
