package com.dspatched.concurrency;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample2 {

    public static void main(String[] args) {
        final PrintTask t1 = new PrintTask("MESSAGE 1");
        final PrintTask t2 = new PrintTask("MESSAGE 2");
        new Thread(new MainLoop(t1, t2)).start();
        new Thread(new MainLoop(t2, t1)).start();
    }

    static class PrintTask {

        private final String message;
        private final Lock lock = new ReentrantLock();

        PrintTask(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public boolean ensureOther(PrintTask other) {
            Boolean myLock = false;
            Boolean otherLock = false;
            try {
                myLock = lock.tryLock();
                otherLock = other.lock.tryLock();
            } finally {
                if (!(myLock && otherLock)) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (otherLock) {
                        other.lock.unlock();
                    }
                }
            }
            return myLock && otherLock;
        }

        public void print(PrintTask printTask) {
            if (ensureOther(printTask)) {
                try {
                    System.out.format("%s: printing out %s message !%n", this.message, printTask.message);
                    System.out.println();
                    printTask.submitMessage(this);
                } finally {
                    lock.unlock();
                    printTask.lock.unlock();
                }
            } else {
                System.out.format("%s: waiting for %s to finish", this.message, printTask.message);
                System.out.println();
            }
        }

        public void submitMessage(PrintTask printTask) {
            System.out.format("%s: submitting my message to %s to print!%n", this.message, printTask.getMessage());
            System.out.println();
        }

    }

    static class MainLoop implements Runnable {
        private PrintTask t1;
        private PrintTask t2;

        public MainLoop(PrintTask t1, PrintTask t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public void run() {
            Random random = new Random();
            while (true) {
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {}
                t2.print(t1);
            }
        }

    }

}
