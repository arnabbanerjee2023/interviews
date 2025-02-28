package multithreading.synchronization;

class MyThread extends Thread {
    private final Counter counter;

    public MyThread(String name, Counter counter) {
        super(name);
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // The Counter object shared between two threads.
        Counter counter = new Counter();

        // Creating thread t1.
        Thread t1 = new MyThread("t1", counter);

        // Creating thread t2.
        Thread t2 = new MyThread("t2", counter);

        t1.start(); // Starting thread t1.
        t2.start(); // Starting thread t2.

        /*
          The below code tells the thread scheduler that the main thread, from where the join() methods called,
          will wait till the threads, t1 and t2, finish their executions. Once the t1 and t2 threads are finished,
          then only the main thread will resume.

          So, while having both the join() methods - t1.join() and t2.join(), we expect the counter value should be
          2000, as both the threads looped 1000 times and the counter gets increased 1000 times by each thread. So,
          the counter should have the value of 2000.
         */
        t1.join();
        t2.join();

        System.out.println(counter.getCount());
    }
}
