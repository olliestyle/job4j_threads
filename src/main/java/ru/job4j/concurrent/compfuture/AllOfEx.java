package ru.job4j.concurrent.compfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * allOf()
 *
 * Это метод возвращает CompletableFuture<Void>, при этом обеспечивает выполнение всех задач.
 * Например, вы зовете всех членов семью к столу. Надо дождаться пока все помоют руки
 */

public class AllOfEx {
    public static CompletableFuture<Void> washHands(String name) {
        return CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ", моет руки");
        });
    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> all = CompletableFuture.allOf(
                washHands("Папа"), washHands("Мама"),
                washHands("Ваня"), washHands("Боря")
        );
        TimeUnit.SECONDS.sleep(5);
    }
}
