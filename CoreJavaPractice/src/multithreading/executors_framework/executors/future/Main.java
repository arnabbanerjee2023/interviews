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

package multithreading.executors_framework.executors.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorService method -
 * 1. submit(Runnable).
 * 2. submit(Callable).
 * 3. submit(Runnable, T).
 * 4. shutdown().
 * 5. shutdownNow().
 * 6. awaitTermination(long, TimeUnit).
 * 7. isTerminated().
 * 8. isShutdown().
 * 9. invokeAll(Collection<Callable<V>>).
 * 10. invokeAny(Collection<Callable<V>>).
 */

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // We are creating an ExecutorService with a single thread.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        /*
          The point to note here, the submit() method here takes a Callable which can return a value.
          Hence we are doing some operations in a separate thread and once the operation is done, now we can
          return the result of the operation too.
         */
        Future<Integer> future = executorService.submit(() -> {
            /*
              Pausing for a second.
              Point to note here, we are surrounding it with a try-catch block. Cause the call() method of the
              Callable interface can throw an exception. We can include the try-catch block here too, that is another
              option to handle the exception here.
              But the run() method of the Runnable interface can't. Hence,
              we need to handle the exception explicitly using a try-catch block inside the run() method, but we can
              ignore that inside the call() method of the Callable interface.
             */
            Thread.sleep(1000);
            return 55;  // Just returning 55.
        });
        try {
            /*
              We are calling the future.get() method which returns the result of the task, ran in a separate thread.
              Earlier this was not possible. As Runnable doesn't return any value. But here the submit method takes
              a Callable which returns a value. The future.get() method waits till the task is completed and then
              returns the result.
             */
            System.out.println(future.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        /*
          This checks whether the future is completed or not. If the future is completed, it will execute the
          if block, otherwise it will proceed with the rest of the program. This doesn't wait for the task to be
          completed.
         */
        if (future.isDone()) {
            System.out.println("Future/Task is done.");
        }

        /*
          Here, we are creating another future, utilizing the same ExecutorService.
          This is time we are not retuning anything once the task is completed. This variation of the submit()
          method uses the Runnable interface. But here too, we are returning a Future object, just that the
          Future object doesn't contain any returned object/value from the task/thread. So, we can use the Future
          object returned to determine different states and can do operations related to Future only
         */
        Future<?> future1 = executorService.submit(() -> System.out.println("Hello World!!!"));

        /*
          The below code is commented out deliberately. Cause if we run the below code, at that point of time, the
          task, submitted above, may not be completed yet. Hence the if block will not execute.
          But if we run a future.get() as you see below, then it will wait for the task to be completed as mentioned
          above. Then if we run the if block, it will execute. Although the future.get() will return null, as there
          is nothing to return from the task.
         */
        /*if (future1.isDone()) {
            System.out.println("Future/Task is done again.");
        }*/

        try {
            // We are trying to get from the future which returns null actually here.
            System.out.println(future1.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // Now, we are trying to check whether the future is done or not. This time it will work as the thread is
        // completed.
        if (future1.isDone()) {
            System.out.println("Future/Task is done again.");
        }

        try {
            /*
              Here, we are using the invokeAll() method of the ExecutorService interface. This method returns a
              List of Future objects. The List contains the Future objects of the tasks submitted. The invokeAll()
              method takes a Collection of Callables.
             */
            List<Future<Integer>> futureList = executorService.invokeAll(Arrays.asList(
                    () -> {
                        System.out.println("Task 1");
                        return 1;
                    },
                    () -> {
                        System.out.println("Task 2");
                        return 2;
                    },
                    () -> {
                        System.out.println("Task 3");
                        return 3;
                    },
                    () -> {
                        System.out.println("Task 4");
                        return 4;
                    }
            ));

            // Looping through the List of Future objects.
            futureList.forEach(future2 -> {
                try {
                    System.out.println(future2.get());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Shutting down the thread pool.
        executorService.shutdown();

        // Once the shutdown() method is called, the pool will be scheduled to be shutdown.
        // So, the below if block will result in true.
        if (executorService.isShutdown()) {
            System.out.println("ExecutorService is shutdown.");
        }

        /*
          But once the pool is shutdown, that doesn't mean that the thread is terminated. It just means that the
          the thread is shutdown and is waiting to be terminated. So, the below if block will result in false.
          Now, if we add some wait time like 10 ms, we are giving the thread ample time to be terminated, then
          the below if block will result in true.
         */
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (executorService.isTerminated()) {
            System.out.println("ExecutorService is terminated.");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
    }
}

