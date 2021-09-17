package ru.job4j.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceUsage {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        pool.submit(() -> System.out.println("Execute in " + Thread.currentThread().getName()));
        pool.submit(() -> System.out.println("Execute in " + Thread.currentThread().getName()));
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Execute in MAIN " + Thread.currentThread().getName());
    }
}
