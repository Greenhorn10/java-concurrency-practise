package in.handson.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThread {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        System.out.println("Submitting task at: "+System.nanoTime());
        scheduledExecutorService.schedule(() -> System.out.println("Task Executing at: "+System.nanoTime()),5, TimeUnit.NANOSECONDS);
        scheduledExecutorService.shutdown();
    }
}
