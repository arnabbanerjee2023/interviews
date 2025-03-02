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
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The below class demonstrates the ReadWriteLock.
 * If a WriteLock is already acquired a thread, no other thread can acquire the WriteLock.
 * On the other hand, any number of threads can acquire the ReadLock at the same time. There is no restriction.
 */
class ReadWriteCounter {
    private int count = 0; // The counter on which we will operate below.

    // Creating the ReadWriteLock object - ReentrantReadWriteLock.
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    // Getting the ReadLock object.
    private final Lock readLock = lock.readLock();

    // Getting the WriteLock object.
    private final Lock writeLock = lock.writeLock();

    // The actual increment() method.
    public void increment() {
        // Acquiring the write lock.
        writeLock.lock();
        try {
            count++; // Incrementing count by 1.
        } finally {
            // Unlocking or releasing the read lock.
            writeLock.unlock();
        }
    }

    // The getCount() method having the ReadLock.
    public int getCount() {
        // Acquiring the read lock.
        readLock.lock();
        try {
            return count; // Returning the count.
        } finally {
            // Unlocking or releasing the write lock.
            readLock.unlock();
        }
    }

}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        // Creating the Counter class declared above.
        ReadWriteCounter counter = new ReadWriteCounter();

        // Creating a task to read from the object.
        Runnable readTask = () -> {
            // Looping through 0 to 9 and reading the count by calling the getCount().
            // The running thread will acquire the read lock in the getCount() method.
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " read: " + counter.getCount());
            }
        };

        // Creating a task to write to the object.
        Runnable writeTask = () -> {
            for (int i = 0; i < 10; i++) {
                // Here we are calling the increment() method of the counter object.
                // The running thread will acquire the write lock here and do the operation.
                counter.increment();
                System.out.println(Thread.currentThread().getName() + " incremented.");
            }
        };

        Thread writeThread = new Thread(writeTask);  // Creating a 'thread to write'.
        Thread readThread1 = new Thread(readTask);  // Creating a 'thread to read'.
        Thread readThread2 = new Thread(readTask);  // Creating 'another thread to read'.

        writeThread.start(); // Starting the 'thread to write'.

        try {
            Thread.sleep(50); // Waiting for 50 ms to let the 'thread to write' to finish.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        readThread1.start(); // Starting the 'thread to read'.
        readThread2.start(); // Starting that 'another thread to read'.

        try {
            writeThread.join(); // The main thread joins here once the 'thread to write' finishes its execution.
            readThread1.join(); // The main thread joins here once the 'thread to read' finishes its execution.
            readThread2.join(); // The main thread joins here once that 'another thread to read' finishes its execution.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
