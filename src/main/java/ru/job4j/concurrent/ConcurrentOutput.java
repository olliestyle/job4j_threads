package ru.job4j.concurrent;

public class ConcurrentOutput {
    public void threadTest() {
        Thread another = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        System.out.println(Thread.currentThread().getName());
        another.start();
        second.start();
    }
}
