package ru.job4j.concurrent.compfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Пример thenApply()
 *
 * Этот метод принимает Function. Также имеет доступ к результату.
 * Как раз благодаря этому, мы можем произвести преобразование полученного результата.
 * Допишем второй пример. Например, вы хотите, чтобы после того, как сын принес молоко, налил вам его в кружку.
 * Однако результат преобразования станет доступным только при вызове get()
 */

public class ThenApply {
    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
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

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко").
                thenApply((product) -> "Сын: я налил тебе в кружку " + product + ". Держи.");
        iWork();
        System.out.println(bm.get());
    }
}
