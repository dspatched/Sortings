package com.dspatched.concurrency;


import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private static final Semaphore[] semaphores = new Semaphore[]
            { new Semaphore(1), new Semaphore(0), new Semaphore(0) };
    private static final int TASK_COUNT = 3;
    private static final int ITERATIONS = 10;

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
                try {
                    semaphores[turnOrder].acquire();
                    System.out.println(message);

                    int nextTaskIndex = (turnOrder + 1) % TASK_COUNT;
                    semaphores[nextTaskIndex].release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}
