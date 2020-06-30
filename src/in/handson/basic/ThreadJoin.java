package in.handson.basic;

public class ThreadJoin {


    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Thread started with Name: "+Thread.currentThread().getName());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread ended with Name: "+Thread.currentThread().getName());
        };

        Thread thread1 = new Thread(runnable,"Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");
        Thread thread3 = new Thread(runnable, "Thread 3");

        thread1.start();

        //start second thread after waiting for 2 seconds
        try {
            thread1.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

        //Make thread 3 to wait for Thread 1 to complete
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread3.start();

        try {
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All Threads are done execution exiting main thread");
    }
}
