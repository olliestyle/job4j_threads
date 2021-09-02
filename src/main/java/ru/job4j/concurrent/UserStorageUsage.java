package ru.job4j.concurrent;

public class UserStorageUsage {
    public static void main(String[] args) throws InterruptedException {
        UserStorage userStorage = new UserStorage();
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                userStorage.add(new SimpleUser(i, 500));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 10001; i <= 20000; i++) {
                userStorage.add(new SimpleUser(i, 1000));
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                userStorage.transfer(i, i + 10000, 400);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(3000);
        System.out.println(userStorage);
    }
}
