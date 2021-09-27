package ru.job4j.concurrent;

public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            System.out.println("In Thread " + Thread.currentThread().getName() + " count = " + count);
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    System.out.println("Waiting");
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("In Thread " + Thread.currentThread().getName() + " count = " + count);
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(3);
        Thread count1 = new Thread(countBarrier::count, "count1");
        Thread count2 = new Thread(countBarrier::count, "count2");
        Thread count3 = new Thread(countBarrier::count, "count3");
        Thread await1 = new Thread(countBarrier::await, "await1");
        Thread await2 = new Thread(countBarrier::await, "await2");
        Thread await3 = new Thread(countBarrier::await, "await3");
        await1.start();
        await2.start();
        await3.start();
        count1.start();
        count2.start();
        count3.start();
    }
}
