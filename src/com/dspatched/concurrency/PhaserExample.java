package com.dspatched.concurrency;

import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class PhaserExample {

    private static final int TASK_COUNT = 3;
    private static final int ITERATIONS = 10;
    private static final Phaser phaser = new Phaser(TASK_COUNT) {
        protected boolean onAdvance(int phase, int registeredParties) {
            return phase >= ITERATIONS - 1 || registeredParties == 0;
        }
    };
    private static final AtomicInteger turn = new AtomicInteger(1);

    public static void main(String[] args) {
        PrintTask printTask1 = new PrintTask("TASK1", 0);
        PrintTask printTask2 = new PrintTask("TASK2", 1);
        PrintTask printTask3 = new PrintTask("TASK3", 2);
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

        PrintTask(String message, int turnOrder) {
            this.message = message;
            this.turnOrder = turnOrder;
        }

        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                while (turn.get() != turnOrder) {
                    Thread.yield(); // Still need busy wait for ordering
                }

                System.out.println(message + " - Iteration " + (i + 1));
                int currentTurn = turn.get();
                currentTurn = (currentTurn % TASK_COUNT) + 1;
                turn.set(currentTurn);
                phaser.arriveAndAwaitAdvance();
            }
        }
    }
}
