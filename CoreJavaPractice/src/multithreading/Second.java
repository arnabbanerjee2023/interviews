package multithreading;

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < 100; j++) {
                // Getting and printing the running thread's name and priority.
                System.out.println("Thread Name: " + Thread.currentThread().getName() + ", Thread Priority: " +
                        Thread.currentThread().getPriority() + ", COUNT: " + i + ", Skipping " + j);
            }
            try {
                // Putting the running thread to sleep for 10ms.
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Second {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        // Setting the thread's name.
        t1.setName("LowPriorityThread");
        // Setting the thread's priority.
        t1.setPriority(Thread.MIN_PRIORITY);

        MyThread t2 = new MyThread();
        // Setting the thread's name.
        t2.setName("NormPriorityThread");
        // Setting the thread's priority.
        t2.setPriority(Thread.NORM_PRIORITY);

        MyThread t3 = new MyThread();
        // Setting the thread's name.
        t3.setName("MaxPriorityThread");
        // Setting the thread's priority.
        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start(); // Start the thread.
        t2.start(); // Start the thread.
        t3.start(); // Start the thread.

        try {
            // Even joining doesn't help to wait for the threads to finish their execution.
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
