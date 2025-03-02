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

class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            // Getting and printing the running thread's name and priority.
            System.out.println("Thread Name: " + Thread.currentThread().getName() + ", Thread Priority: " +
                    Thread.currentThread().getPriority() + ", COUNT: " + i);
            // A hint to the scheduler that the current thread is willing to yield its current use of a processor.
            // Obviously, the scheduler is free to ignore that hint.
            Thread.yield();
        }
    }
}

public class Third {
    public static void main(String[] args) {
        Thread t1 = new MyThread2();
        t1.setName("First Thread");

        Thread t2 = new MyThread2();
        t2.setName("Second Thread");

        t1.start();
        t2.start();
    }
}