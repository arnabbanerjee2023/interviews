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

package multithreading.synchronization.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The below class shows a demo on the ReentrantLock.
 * <p>
 * ReentrantLock - Means Re-entrant Lock, means re-entry within the lock is possible only if the same thread is trying
 * to do so.
 * This lock keeps track of acquiring the lock. If a thread acquires the lock 2 times, then the thread should
 * release or unlock the lock 2 times too. Then only the lock will be released.
 */
class ReentrantExample {
    // Creating the Lock object - ReentrantLock.
    private final Lock lock = new ReentrantLock();

    // This method - outerMethod() will be called first. Then this method will call the innerMethod().
    public void outerMethod() {
        // We are acquiring the lock. As we are calling from the main thread, the main thread acquires the lock.
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " - Outer Method!");
            innerMethod(); // Calling the innerMethod.
        } finally {
            // Unlocking the lock or releasing the lock.
            lock.unlock();
        }
    }

    // This method is called from the outerMethod().
    public void innerMethod() {
        /*
          Again we are acquiring the lock. But the lock is already acquired by the main thread.
          Point is - this is a ReentrantLock. And if the same thread is trying to acquire the same lock again,
          it can do so as this is a Re-entrant lock. So this is fine.
         */
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " - Inner Method!");
        } finally {
            lock.unlock();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        // Creating a Runnable task which calls the withdraw() method.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // Calling the withdraw() method from the running thread.
                    bankAccount.withdraw(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(runnable);
        t1.setName("t1");

        Thread t2 = new Thread(runnable);
        t2.setName("t2");

        Thread t3 = new Thread(runnable);
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();

        // ReentrantExample demo
        ReentrantExample reentrantExample = new ReentrantExample();
        reentrantExample.outerMethod(); // Calling the outerMethod().
    }
}
