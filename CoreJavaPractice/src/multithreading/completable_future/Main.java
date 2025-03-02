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

package multithreading.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
          The CompletableFuture was introduced in Java 8 to handle asynchronous operations.
          Here the supplyAsync takes a Supplier. The supplier basically spawns a new daemon thread to run the task and
          returns the result.
          The supplyAsync operations is asynchronous and the main thread doesn't wait for the task to be completed.
          To make the main thread wait for the task to be completed, we need to access the CompletableFuture.get()
          method, which returns the actual result.
         */
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " is running and service is started");
            try {
                Thread.sleep(2000); // Sleeping for 2 seconds.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "OK";
        }).thenApply(value -> value + value);

        // Waiting for the task to be completed and get the result.
        System.out.println(completableFuture1.get());

        // Creating 2nd CompletableFuture task.
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " is running and service is started");
            try {
                Thread.sleep(2000); // Sleeping for 2 seconds.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "OK";
        });

        // Creating 3rd CompletableFuture task.
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " is running and service is started");
            try {
                Thread.sleep(2000); // Sleeping for 2 seconds.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "OK";
        });

        /*
          The allOf() method of CompletableFuture combines and runs all the CompletableFuture tasks in parallel,
          in separate threads, as specified. But the get() method here, in this case, returns null, as the return
          type is mentioned as void.
          If any of the threads fails or throws an exception, the get() call evaluates to exception too.
         */
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFuture2, completableFuture3);

        // The below statement will print null as in this case of allOf(), the CompletableFuture returns void.
        //System.out.println(completableFuture.get()); // Waiting for the task to be completed and get the result.

        // The main thread joins when all the daemon threads from the CompletableFuture are completed.
        completableFuture.join();

        // Printing the name of the main thread.
        System.out.println(Thread.currentThread().getName()); // Checking the thread - main.

    }
}
