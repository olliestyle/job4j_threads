package ru.job4j.concurrent.compfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * thenCombine()
 *
 * Данный метод используется, если действия могут быть выполнены независимо друг от друга.
 * Причем в качестве второго аргумента, нужно передавать BiFunction – функцию,
 * которая преобразует результаты двух задач во что-то одно.
 * Например, первого сына вы посылаете купить хлеб, а второго сходить за молоком.
 * В этой ситуации можно поступить так
 */

public class ThenCombine {

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

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> result = buyProduct("Молоко")
                .thenCombine(buyProduct("Хлеб"), (r1, r2) -> "Куплены " + r1 + " и " + r2);
        iWork();
        System.out.println(result.get());
    }
}
