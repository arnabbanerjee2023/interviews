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
