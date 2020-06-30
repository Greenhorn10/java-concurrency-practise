package in.handson.basic;

public class BasicThread extends Thread{
    @Override
    public void run() {
        System.out.println("Running Thread Name: "+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Running Thread: "+Thread.currentThread().getName());

        Thread thread = new BasicThread();
        thread.setName("Basic Thread");
        thread.start();
    }
}
