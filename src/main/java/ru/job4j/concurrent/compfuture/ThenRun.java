package ru.job4j.concurrent.compfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Пример thenRun()
 * Этот метод ничего не возвращает, а позволяет выполнить задачу типа Runnable после выполнения асинхронной задачи.
 * Допишем первый пример, чтобы сын шел мыть руки
 */

public class ThenRun {
    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            System.out.println("Вы: Я работаю");
            TimeUnit.SECONDS.sleep(1);
            count++;
        }
    }

    private static CompletableFuture<Void> goToThrash() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Сын: Мам/Пам, я пошел выносить мусор");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Сын: Мам/Пап, я вернулся!");
        });
    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> completableFuture = goToThrash();
        completableFuture.thenRun(() -> {
            int count = 0;
            while (count < 3) {
                System.out.println("Сын: я мою руки");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
            System.out.println("Сын: Я помыл руки");
        });
        iWork();
    }
}
