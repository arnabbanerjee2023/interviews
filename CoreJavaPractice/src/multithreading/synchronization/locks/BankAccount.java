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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    /**
     * Creating a Lock object.
     * <p>
     * Two tryLock methods
     * <p>
     * 1. tryLock(): It will try to acquire the lock, if the lock is available, it will acquire it, otherwise it will
     * proceed with the rest of the code. It returns boolean. Suppose, tryLock() is part of if() statement -
     * if (lock.tryLock()) {
     *     // if lock is acquired.
     * } else {
     *     // if lock is not acquired.
     * }
     * In this case, tryLock() inside the if statement tries to acquire the lock, if it is successful to acquire the
     * lock, it will continue executing the if block. Otherwise, it will execute the else block. But it will not wait
     * to acquire the lock.
     * <p>
     * 2. tryLock(int, TimeUnit): It will try to acquire the lock, and if the lock is not available,
     * it will wait for the mentioned time and if it is successful to acquire the lock within the specified time, it
     * will continue to execute the if statement. But if it is not successful to acquire the lock (the lock is not
     * released or still busy), then it will execute the rest of the code. It also returns boolean. Suppose, it is part
     * of an if block -
     * if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
     *     // if lock is acquired.
     * } else {
     *     // if lock is not acquired.
     * }
     * In this case, it will try to acquire the lock, and if the lock is not available at that point of time, it will
     * wait for 1000 milliseconds, as specified. And then if it is successful to acquire the lock within that
     * specified time - 1000 milliseconds, it will execute the if block, otherwise, it will execute the else block.
     */
    private final Lock lock = new ReentrantLock();

    // The withdraw() method.
    public void withdraw(int amount) throws InterruptedException {
        /*
            Trying to acquire the lock using the tryLock(int, TimeUnit) method.
            The first parameter is the wait time in integer - 5000 and the second and last parameter is the unit of the
            time, which milliseconds.
         */
        if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
            System.out.println(Thread.currentThread().getName() + " attempting withdrawal of Rs. " + amount);
            if (amount <= balance) {
                System.out.println(Thread.currentThread().getName() + " proceeding to withdraw Rs. " + amount);
                try {
                    // Putting the thread to sleep for some time, denoting some backend operations being done, like
                    // DB operations or REST calls, etc.
                    Thread.sleep(3000);
                    balance -= amount;  // Deducting the amount from the balance.
                    System.out.println(Thread.currentThread().getName() + " completed withdrawal of Rs. " + amount);
                    System.out.println(Thread.currentThread().getName() + " Updated Balance - Rs. " + balance);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // Releasing or unlocking the lock in finally block.
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " - Insufficient Balance!");
            }
        } else {
            // If the lock is already acquired by a thread, and another thread is trying to acquire the lock, the call
            // will come to this else block, and the below statements will bve executed.
            System.out.println(Thread.currentThread().getName() + " - SYSTEM BUSY!!!");

            // Now the thread will try to acquire the lock again. And if the lock is already acquired, it will wait
            // here. Cause the lock.lock() is a blocking operation. Hence, when the lock is released, then the current
            // thread waiting here will acquire the lock.
            lock.lock();

            // After acquiring the lock, the thread will call this method recursively, and the lock.tryLock() will
            // pass as true this time as the running thread has already acquired the lock and then trying to access the
            // method.
            this.withdraw(amount);
        }
    }
}
