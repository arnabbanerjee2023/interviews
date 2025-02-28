package multithreading;

class MyThread3 extends Thread {
    @Override
    public void run() {
        while (true) { // Infinite loop.
            System.out.println("Hello World from Infinite loop.");
        }
    }
}

public class Fourth {
    public static void main(String[] args) {
        // This is a user thread.
        Thread t1 = new MyThread3();
        //t1.start();

        // Daemon Threads.
        Thread t2 = new MyThread3();
        t2.setDaemon(true); // Makes the thread a Daemon thread, so that it can run in the background.
        t2.start();
        System.out.println("Main Thread Done.");
    }
}
