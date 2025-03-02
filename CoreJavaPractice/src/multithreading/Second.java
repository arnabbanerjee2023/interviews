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

package multithreading;

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < 100; j++) {
                // Getting and printing the running thread's name and priority.
                System.out.println("Thread Name: " + Thread.currentThread().getName() + ", Thread Priority: " +
                        Thread.currentThread().getPriority() + ", COUNT: " + i + ", Skipping " + j);
            }
            try {
                // Putting the running thread to sleep for 10ms.
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Second {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        // Setting the thread's name.
        t1.setName("LowPriorityThread");
        // Setting the thread's priority.
        t1.setPriority(Thread.MIN_PRIORITY);

        MyThread t2 = new MyThread();
        // Setting the thread's name.
        t2.setName("NormPriorityThread");
        // Setting the thread's priority.
        t2.setPriority(Thread.NORM_PRIORITY);

        MyThread t3 = new MyThread();
        // Setting the thread's name.
        t3.setName("MaxPriorityThread");
        // Setting the thread's priority.
        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start(); // Start the thread.
        t2.start(); // Start the thread.
        t3.start(); // Start the thread.

        try {
            // Even joining doesn't help to wait for the threads to finish their execution.
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
