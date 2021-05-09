package ru.job4j.concurrent;

/**
 * Нить переходит в состояние TERMINATED, когда все операторы в методе run() выполнены.
 * Планировщик выделяет разное время для каждой нити,
 * по этой причине флаг прерывания выставляется в произвольное время.
 */
public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(count++);
            }
        });
        thread.start();
        Thread.sleep(1);
        thread.interrupt();
    }
}
