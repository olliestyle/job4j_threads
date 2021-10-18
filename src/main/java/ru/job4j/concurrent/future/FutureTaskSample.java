package ru.job4j.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskSample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return 5;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        for (int i = 0; i < 30; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(futureTask.get());
    }
}
