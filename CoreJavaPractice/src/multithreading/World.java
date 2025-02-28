package multithreading;

public class World extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            // Here it is printing Hello about 10 million times from the Thread-0 thread.
            //System.out.println("World.");
            // Here it is printing the current thread's name.
            System.out.println(Thread.currentThread().getName());
            try {
                // We are putting the thread to sleep for 100ms. So that the main thread can continue the execution.
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
