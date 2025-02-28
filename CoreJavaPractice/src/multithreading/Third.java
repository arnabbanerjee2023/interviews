package multithreading;

class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            // Getting and printing the running thread's name and priority.
            System.out.println("Thread Name: " + Thread.currentThread().getName() + ", Thread Priority: " +
                    Thread.currentThread().getPriority() + ", COUNT: " + i);
            // A hint to the scheduler that the current thread is willing to yield its current use of a processor.
            // Obviously, the scheduler is free to ignore that hint.
            Thread.yield();
        }
    }
}

public class Third {
    public static void main(String[] args) {
        Thread t1 = new MyThread2();
        t1.setName("First Thread");

        Thread t2 = new MyThread2();
        t2.setName("Second Thread");

        t1.start();
        t2.start();
    }
}