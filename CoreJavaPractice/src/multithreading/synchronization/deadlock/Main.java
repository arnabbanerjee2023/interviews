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

package multithreading.synchronization.deadlock;

class Pen {
    public void writeWithPaperAndPen(Paper paper) {
        // Acquiring the lock on the paper object to avoid the deadlock in this scenario.
        synchronized (paper) {
            System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to acquire paper "
                    + paper);
            paper.finishWriting();
        }
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished writing using pen " + this);
    }

    @Override
    public String toString() {
        return "PenObject";
    }
}

class Paper {
    public void writeWithPaperAndPen(Pen pen) {
        // Acquiring the lock on the pen object to avoid the deadlock in this scenario.
        synchronized (pen) {
            System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and trying to acquire pen "
                    + pen);
            pen.finishWriting();
        }
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished writing using paper " + this);
    }

    @Override
    public String toString() {
        return "PaperObject";
    }
}

public class Main {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1 = new Thread(() -> pen.writeWithPaperAndPen(paper));
        Thread t2 = new Thread(() -> paper.writeWithPaperAndPen(pen));

        t1.start();
        t2.start();
    }
}
