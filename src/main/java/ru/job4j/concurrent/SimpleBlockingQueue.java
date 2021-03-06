package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int block;

    public SimpleBlockingQueue(int block) {
        this.block = block;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == block) {
            wait();
        }
        queue.offer(value);
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        T t = queue.poll();
        notify();
        return t;
    }

    public synchronized int size() {
        return queue.size();
    }
}

class Consumer implements Runnable {

    SimpleBlockingQueue simpleBlockingQueue;

    public Consumer(SimpleBlockingQueue simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        try {
            simpleBlockingQueue.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    SimpleBlockingQueue simpleBlockingQueue;

    public Producer(SimpleBlockingQueue simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        try {
            simpleBlockingQueue.offer(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
