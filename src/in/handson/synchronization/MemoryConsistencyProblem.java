package in.handson.synchronization;

public class MemoryConsistencyProblem {
    public static boolean bool=false;
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
          while (!bool){}
            System.out.println("Bool value is updated to true");
          while(bool){}
            System.out.println("Bool value is updated to false");
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(1000);
        System.out.println("Change value of bool");
        bool = true;

        Thread.sleep(1000);
        System.out.println("Change back the bool value");
        bool = false;
    }
}
