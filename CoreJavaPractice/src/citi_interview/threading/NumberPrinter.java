package citi_interview.threading;

public class NumberPrinter {
    private int number = 1;
    private final Object lock = new Object();
    private boolean printOdd = true;

    public void printOddNumbers() {
        while (number <= 100) {
            synchronized (lock) {
                while (!printOdd) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + number);
                number++;
                printOdd = false;
                lock.notify();
            }
        }
    }

    public void printEvenNumbers() {
        while (number <= 100) {
            synchronized (lock) {
                while (printOdd) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + number);
                number++;
                printOdd = true;
                lock.notify();
            }
        }
    }
}
