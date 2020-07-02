package in.handson.problems;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosopher {
    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];
        for(int i=0;i<forks.length;i++){
            forks[i] = new Object();
        }

        for(int i=0;i<philosophers.length;i++){
            Object leftFork = forks[i];
            Object rightFork = forks[(i+1) % forks.length];
            if(i==philosophers.length-1) {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            }else{
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }

            Thread thread = new Thread(philosophers[i], "Philosopher: "+(i+1));
            thread.start();
        }
    }

}

class Philosopher implements Runnable{
    private Object leftChopStick;
    private Object rightChopStick;

    public Philosopher(Object leftChopStick, Object rightChopStick) {
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
    }

    public void performAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " action: "+action);
        Thread.sleep((int)(Math.random() * 100));
    }

    @Override
    public void run() {
        try {
            while (true) {
                performAction(System.nanoTime() + ": Thinking");
                synchronized (leftChopStick){
                    performAction(System.nanoTime()+ " : Pick left Chopstick");
                    synchronized (rightChopStick){
                        performAction(System.nanoTime()+ " : Pick right chopstick - start eating");
                        performAction(System.nanoTime()+" : Put done right chopstick");
                    }
                    performAction(System.nanoTime()+" :Put done left chopstick - start thinking");
                }
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return;
        }
    }
}