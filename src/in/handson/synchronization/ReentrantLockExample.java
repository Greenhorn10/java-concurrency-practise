package in.handson.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static void main(String[] args) {
        //ReentrantCounter reentrantCounter = new ReentrantCounter();
        ReentrantLockCounter reentrantLockCounter = new ReentrantLockCounter();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                System.out.println("Counter for Thread 1: "+reentrantLockCounter.incrementAndGet());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                System.out.println("Counter for Thread 2: "+reentrantLockCounter.incrementAndGet());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();

    }
}

class ReentrantCounter{
    int value = 0;
    ReentrantLock reentrantLock = new ReentrantLock();
    void increment(){
        reentrantLock.lock();
        value = value + 1;
        reentrantLock.unlock();
    }

    int getCounter(){
        return value;
    }
}

class ReentrantLockCounter{
    int count = 0;
    ReentrantLock reentrantLock = new ReentrantLock();

    int incrementAndGet() throws InterruptedException {
        System.out.println("Is locked by other thread: "+reentrantLock.isLocked());

        System.out.println("Is locked by current thread: "+reentrantLock.isHeldByCurrentThread());

        //waiting for 2nd Thread to get access after 1st releases lock in 1sec
        boolean isAcquired = reentrantLock.tryLock(1, TimeUnit.SECONDS);

        if(isAcquired){
            try {
                Thread.sleep(1000);
                count = count + 1;
            } catch (InterruptedException e) {
                throw new IllegalArgumentException();
            }finally {
                reentrantLock.unlock();
            }
        }

        return count;
    }
}
