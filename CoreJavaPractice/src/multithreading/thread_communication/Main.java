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

package multithreading.thread_communication;

/**
 * The SharedResources class having the Producer-Consumer problem with the produce() and consume() methods.
 */
class SharedResources {
    private int data;  // The data being operated on.
    private boolean hasData;  // The hasData flag.

    public synchronized void produce(int value) {
        if (this.isHasData()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            data = value;
            hasData = true;
            System.out.println(Thread.currentThread().getName() + " produced: " + value);
            System.out.println(Thread.currentThread().getName() + " new data value: " + this.getData());
            notify();
        }
    }

    public synchronized void consume() {
        if (this.isHasData()) {
            hasData = false;
            System.out.println(Thread.currentThread().getName() + " consumed: " + this.getData());
            data = 0;
            System.out.println(Thread.currentThread().getName() + " new data value: " + this.getData());
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean isHasData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    public int getData() {
        return data;
    }
}

public class Main {
    public static void main(String[] args) {
        SharedResources sharedResources = new SharedResources();
        sharedResources.setHasData(false);

        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                sharedResources.produce(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                sharedResources.consume();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producerThread.setName("Producer Thread");
        consumerThread.setName("Consumer Thread");

        producerThread.start();
        consumerThread.start();
        /*new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                sharedResources.consume();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();*/
    }
}
