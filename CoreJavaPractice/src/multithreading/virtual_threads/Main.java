package multithreading.virtual_threads;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final int THREADS_COUNT = 100_000;

        List<Thread> threads = new ArrayList<>();
        Runnable runnable = () -> System.out.println("Current Thread: " + Thread.currentThread().getName());

        // This is an example of real threads.
        /*for (int i = 0; i < THREADS_COUNT; i++) {
            Thread thread =
                    new Thread(runnable);
            thread.setDaemon(true);
            thread.start();
            threads.add(thread);
        }*/

        // This is an example of virtual threads.
        for (int i = 0; i < THREADS_COUNT; i++) {
            Thread thread = new Thread(runnable);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
                System.out.println("Thread completed: " + thread.getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
