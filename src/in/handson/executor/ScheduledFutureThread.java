package in.handson.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledFutureThread {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Executing task");

        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(task,1,1, TimeUnit.SECONDS);

        while(true){
            Thread.sleep(1000);
            if(count.get() == 5){
                System.out.println("Cancelling the scheduled task after reaching 5 count");
                scheduledFuture.cancel(true);
                scheduledExecutorService.shutdown();
                break;
            }
            count.getAndIncrement();
        }
    }
}
