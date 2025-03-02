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

/**
 * Thread lifecycle -
 * 1. New: A thread is created but not started yet.
 * 2. Runnable: After calling the start() method, the thread becomes runnable. The thread is ready to run.
 * It is currently waiting for CPU time.
 * 3. Running: The thread is running inside the CPU or executing and performing the assigned task.
 * 4. Blocked/Waiting: The thread is waiting or blocked for a resource or for another thread to perform an action.
 * 5. Terminated: This thread has completed its execution and finished.
 * <p>
 * Thread methods -
 * 1. Start(): Starts the thread or the execution of the thread. Makes the state of the thread Runnable. So that it can
 * be picked up by the thread scheduler to be executed inside the CPU Core.
 * 2. Join(): The calling thread waits till the called thread finished its execution, and calling thread joins the
 * called thread at the end of the execution of the called thread. So then the state of the called state becomes
 * Terminated, and the state of the calling thread becomes Runnable.
 * 3. setName(): To set the thread's name.
 * 4. setPriority(): To set the thread's priority - MIN_PRIORITY (1), NORM_PRIORITY (5) (default), and
 * MAX_PRIORITY (10).
 * 5. Yield(): To inform the scheduler that the running thread can release the CPU and can let other threads continue.
 */

public class First {
    public static void main(String[] args) {
        // Java starts with one thread - main thread.
        System.out.println("Hello World.");
        // To check the thread being run - the only thread being run - main thread.
        System.out.println(Thread.currentThread().getName());

        // Thread-0.
        // Creating an object of the thread class.
        World world = new World();
        // Setting the name of the thread.
        world.setName("World Thread");
        // Starting the thread.
        world.start();

        // Thread-1.
        // Creating an object of type Runnable.
        NewWorld newWorld = new NewWorld();
        // Creating a Thread object from the Runnable.
        Thread newWorldThread = new Thread(newWorld);
        // Setting the name of the thread.
        newWorldThread.setName("New World Thread");
        // Starting the thread.
        newWorldThread.start();

        // Thread-2.
        // Dynamically creating a thread using a Runnable passed as lambda expression and starting the thread.
        new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                // Here it is printing Hello about 10 million times from the Thread-0 thread.
                //System.out.println("World.");
                // Here it is printing the current thread's name.
                System.out.println(Thread.currentThread().getName() + " : " + "ARNAB");
            }
        }).start();

        // main thread.
        for (int i = 1; i <= 1000; i++) {
            // Here it is printing Hello about 10 million times from the main thread.
            //System.out.println("Hello.");
            // Here it is printing the current thread's name.
            System.out.println(Thread.currentThread().getName());
            try {
                // Putting the main thread to sleep for 1000 ms.
                Thread.sleep(1000);
                // The main thread joins once the world thread is done with the execution and in Terminated state.
                world.join();
                // The main thread joins once the newWorldThread thread is done with the execution
                // and in Terminated state.
                newWorldThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
