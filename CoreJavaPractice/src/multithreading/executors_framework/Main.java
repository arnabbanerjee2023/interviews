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

package multithreading.executors_framework;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[10];

        for (int j = 1; j <= 10; j++) {
            threads[j - 1] = new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Factorial of " + i + " is " + Factorial.getFactorial(i));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads[j - 1].start();
        }

        Arrays.stream(threads)
                .toList()
                .forEach(thread -> {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
        });

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
    }
}

class Factorial {
    public static int getFactorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * getFactorial(n - 1);
    }
}
