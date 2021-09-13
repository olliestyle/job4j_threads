package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        Integer value = count.get();
        if (count.get() == null) {
            count.set(0);
            value = count.get();
        }
        count.compareAndSet(value, value + 1);
    }

    public int get() {
        Integer toReturn = count.get();
        if (toReturn == null) {
            throw new IllegalStateException("No value to get");
        }
        return toReturn;
    }

    public static void main(String[] args) throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
               casCount.increment();
           }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(casCount.get() + " Thread111");
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(casCount.get() + " Thread222");
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(casCount.get() + " Thread333");
            }
        });
        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(casCount.get() + " Thread444");
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
    }
}
