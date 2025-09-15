package com.dspatched.concurrency;

public class SimpleSynchronizedExample {

    private static final Object lock = new Object();
    private static int turn = 1;
    private static final int TASK_COUNT = 3;
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
        private final int turnOrder;

        PrintTask(String message, int turnOrder) {
            this.message = message;
            this.turnOrder = turnOrder;
        }

        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized (lock) {
                    while (turn != turnOrder) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    System.out.println(message);
                    turn = (turn % TASK_COUNT) + 1;
                    lock.notifyAll();
                }
            }
        }
    }

}
