package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count;

    public CASCount(Integer count) {
        this.count = new AtomicReference<>(count);
    }

    public void increment() {
        Integer value;
        do {
            value = count.get();
        } while (!count.compareAndSet(value, value + 1));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CASCount casCount = new CASCount(0);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
               casCount.increment();
           }
        });
        Thread thread6 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                casCount.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(casCount.get() + " Thread111");
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(casCount.get() + " Thread222");
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(casCount.get() + " Thread333");
            }
        });
        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(casCount.get() + " Thread444");
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
    }
}
