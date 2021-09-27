package ru.job4j.concurrent;

public class ThreadStopJoin {
    /**
     * Эта схема является шаблоном.
     * Если используются методы sleep, join или wait, то нужно в блоке catch вызвать прерывание.
     * Thread (in this case - main thread), in which we call join() method on another thread will wait for its completion
     * Main thread wait until thread will complete its run() method
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("start...");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    System.out.println(Thread.currentThread().getState());

                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
    }
}
