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

package multithreading.executors_framework.executors.cyclic_barrier;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int numberOfServices = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfServices);

        Callable<String> callable = () -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " is running and service is started.");
            System.out.println("Thread: " + Thread.currentThread().getName() + " is waiting at the barrier.");
            Thread.sleep(2000);
            cyclicBarrier.await();
            return "OK";
        };

        executorService.submit(callable);
        executorService.submit(callable);
        executorService.submit(callable);

        executorService.shutdown();
        System.out.println(Thread.currentThread().getName());
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
