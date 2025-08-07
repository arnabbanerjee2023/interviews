package citi_interview.threading;

import java.util.ArrayList;
import java.util.List;

class NumberOperation {
    List<Integer> first;
    List<Integer> second;

    public NumberOperation() {
        first = new ArrayList<>();
        second = new ArrayList<>();
        for (int i = 1; i < 100; i = i + 2) {
            first.add(i);
        }
        for (int i = 2; i < 100; i = i + 2) {
            second.add(i);
        }
    }

    public synchronized void first() {
        for (Integer i : first) {
            System.out.println(i);
            try {
                if (i < first.get(first.size() - 1)) { // To avoid deadlock.
                    wait();  // Sequence 1.
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            notify();  // Sequence 2.
        }
    }

    public synchronized void second() {
        for (Integer i : second) {
            System.out.println(i);
            notify();  // Sequence 1.
            try {
                wait();  // Sequence 2.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class NumberThreading {
    public static void main(String[] args) {
        NumberOperation op = new NumberOperation();
        new Thread(op::first).start();
        new Thread(op::second).start();
    }
}
