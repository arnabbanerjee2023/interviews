package multithreading.thread_pool_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService =
                new ThreadPoolExecutor(1,
                        5,
                        0L,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(6));

        executorService.execute(() ->
                System.out.println("Hello from thread - " + Thread.currentThread().getName()));

        for (int i = 0; i < 10; i++) {
            executorService.execute(() ->
                    System.out.println("Hello from thread - " + Thread.currentThread().getName()));
        }

        executorService.shutdown();
    }
}
