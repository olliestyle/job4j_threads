package ru.job4j.concurrent.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<Integer> integerFuture = executorService.submit(() -> 5);
        Integer res = integerFuture.get();
        System.out.println(res);
        executorService.shutdown();
    }
}
