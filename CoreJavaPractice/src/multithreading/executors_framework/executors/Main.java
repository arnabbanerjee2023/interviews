/**
 * Copyright © 2025 ARNAB BANERJEE. All rights reserved.
 * <p>
 * This program is proprietary and confidential. It is licensed for use only by authorized users.
 * Unauthorized use, copying, distribution, or modification is strictly prohibited and may result
 * in severe civil and criminal penalties.
 * <p>
 * THIS PROGRAM IS PROVIDED 'AS IS' WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR NONINFRINGEMENT.
 * <p>
 * ARNAB BANERJEE DISCLAIMS ALL LIABILITY FOR DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS PROGRAM, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package multithreading.executors_framework.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Executors Framework
 * 1. Executor: A simple interface (Functional Interface in reality) that has a single method - execute(Runnable),
 * which executes a given Runnable task.
 * 2. Executors: A utility class that provides factory methods for creating different executors, such as below.
 *      Different Executor instances (ExecutorService) -
 *      a) newFixedThreadPool(int).
 *      b) newSingleThreadExecutor().
 *      C) newCachedThreadPool().
 * 2. ExecutorService: An extension of the Executor interface that provides methods for managing the lifecycle of
 * tasks, such as shutting down the executor and awaiting termination.
 * 3. ScheduledExecutorService: An extension of the ExecutorService interface that provides methods for scheduling
 * tasks to run at a fixed rate or with a fixed delay.
 */
public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int j = 1; j <= 10; j++) {
            /*
              For an ExecutorService, we submit the request. The submit() methods takes a Runnable, so we are passing
              a lambda of the same here. Once it is submitted, we don't need to start it, as it will be started
              automatically. In reality, there is no start() method too in an ExecutorService.
              Unlike execute() method of Executor interface, the submit() method returns a Future.
             */
            executor.submit(() -> {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Factorial of " + i + " is " + Factorial.getFactorial(i));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        // To shut down the thread pool completely. This ensures that all the threads are done with their work, and
        // it can shut down the thread pool now.
        executor.shutdown();
        try {
            /*
              The awaitTermination() method waits till the specified time in specified time unit. If all the threads
              are not done within that specified time, it will throw an error. Otherwise, if all the threads are done
              within the specified time, it will progress to the next statement, and it will not wait for the
              remaining time. Like the specified time is 100 seconds, and the tasks/threads are completed within
              50 seconds, it will not wait for the rest of 50 seconds, rather it will progress to the next
              statement.
             */
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
    }
}

class Factorial {
    public static int getFactorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * getFactorial(n - 1);
    }
}

