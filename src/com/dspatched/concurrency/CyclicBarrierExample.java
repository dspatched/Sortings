package com.dspatched.concurrency;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierExample {

    private static final AtomicInteger turn = new AtomicInteger(1);
    private static final int TASK_COUNT = 3;
    private static final int ITERATIONS = 10;
    private static final CyclicBarrier barrier = new CyclicBarrier(TASK_COUNT, () -> {
        int currentTurn = turn.get();
        currentTurn = (currentTurn % TASK_COUNT) + 1;
        turn.set(currentTurn);
    });

    public static void main(String[] args) {
        ReentrantLockExample1.PrintTask printTask1 = new ReentrantLockExample1.PrintTask("TASK1", 1);
        ReentrantLockExample1.PrintTask printTask2 = new ReentrantLockExample1.PrintTask("TASK2", 2);
        ReentrantLockExample1.PrintTask printTask3 = new ReentrantLockExample1.PrintTask("TASK3", 3);
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
        private final int turnOrder;

        PrintTask(String message, Integer turnOrder) {
            this.message = message;
            this.turnOrder = turnOrder;
        }

        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                try {
                    while (turn.get() != turnOrder) {
                        Thread.yield();
                    }
                    System.out.println(message);
                    barrier.await();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
