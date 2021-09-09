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

    public synchronized void offer(T value) {
        queue.offer(value);
        notify();
    }

    public synchronized T poll() {
        while (queue.peek() == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return queue.poll();
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
        Object obj = simpleBlockingQueue.poll();
    }
}

class Producer implements Runnable {

    SimpleBlockingQueue simpleBlockingQueue;

    public Producer(SimpleBlockingQueue simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        simpleBlockingQueue.offer(new Random().nextInt(10));
    }
}
