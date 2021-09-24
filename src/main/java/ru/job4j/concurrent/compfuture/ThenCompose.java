package ru.job4j.concurrent.compfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * thenCompose()
 *
 * Данный метод используется, если действия зависимы.
 * Т.е. сначала должно выполниться одно, а только потом другое.
 * Например, вам принципиально, чтобы сын сначала выбросил мусор, а только потом сходил за молоком.
 * В ситуации можно записать так
 */

public class ThenCompose {
    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 12) {
            System.out.println("Вы: Я работаю");
            TimeUnit.SECONDS.sleep(1);
            count++;
        }
    }

    private static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Сын: Мам/Пам, я пошел в магазин");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сын: Мам/Пап, я купил " + product);
                    return product;
                }
        );
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

    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> result = buyProduct("Молоко").thenCompose(a -> goToThrash());
        iWork();
    }
}
