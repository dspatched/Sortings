package com.dspatched.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample1 {

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static volatile AtomicInteger turn = new AtomicInteger(1);
    static final Integer TASK_COUNT = 3;
    private static final int ITERATIONS = 10;

    public static void main(String[] args) {
        PrintTask printTask1 = new PrintTask("TASK1", 1);
        PrintTask printTask2 = new PrintTask("TASK2", 2);
        PrintTask printTask3 = new PrintTask("TASK3", 3);
        Thread t1 = new Thread(printTask1);
        Thread t2 = new Thread(printTask2);
        Thread t3 = new Thread(printTask3);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static class PrintTask implements Runnable {

        private final String message;
        private final Integer turnOrder;

        PrintTask(String message, Integer turnOrder) {
            this.message = message;
            this.turnOrder = turnOrder;
        }

        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                lock.lock();
                try {
                    while (turn.get() != turnOrder) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println(message);

                    int nextTurn = (turn.get() % TASK_COUNT) + 1;
                    turn.set(nextTurn);
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
