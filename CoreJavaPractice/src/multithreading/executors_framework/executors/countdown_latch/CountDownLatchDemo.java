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

package multithreading.executors_framework.executors.countdown_latch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        int numberOfServices = 3; // Basically the number of threads.

        // Creating a fixed thread pool of size mentioned above.
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);

        /*
          Creating the CountDownLatch from the number of threads mentioned above.
          The CountDownLatch class works like a marker. It is initialized with the number of threads, mentioned above.
          It gets assigned to each thread, and when each threads completes their execution, it will count down by 1.
          This way, once it reaches 0, the main thread will continue its execution.
          Actually, the main thread will wait for the CountDownLatch to reach 0, by calling the await() method.
         */
        CountDownLatch countDownLatch = new CountDownLatch(numberOfServices);

        // Creating the Callable task here. The same task will be executed by each thread.
        Callable<String> callable = () -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " is running and service is started.");
            Thread.sleep(2000);
            countDownLatch.countDown();
            return "OK";
        };

        // Submitting the task thrice.
        executorService.submit(callable);
        executorService.submit(callable);
        executorService.submit(callable);

        try {
            // Waiting for the CountDownLatch to reach 0. Means waiting for all the threads to complete their execution
            // and reach this point in the code, where the CountDownLatch became 0. Next, the main thread continues.
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Shutting down the executor service.
        executorService.shutdown();
        System.out.println(Thread.currentThread().getName()); // Printing the name of the main thread.
    }
}

/*class DependentTask implements Callable<String> {

    private final CountDownLatch countDownLatch;

    public DependentTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Thread: " + Thread.currentThread().getName() + " is running and service is started.");
        Thread.sleep(2000);
        return "OK";
    }
}*/
