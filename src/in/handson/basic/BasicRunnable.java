package in.handson.basic;

public class BasicRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread Running Name: "+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Thread Running Name: "+Thread.currentThread().getName());
        Thread thread = new Thread(new BasicRunnable());
        thread.setName("Basic Runnable");
        thread.start();
    }
}
