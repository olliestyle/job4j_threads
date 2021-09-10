package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleBlockingQueueTest {
    @Test
    public void waitQueueIsEmpty() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread consumer = new Thread(new Consumer(queue));
        Thread consumer1 = new Thread(new Consumer(queue));
        Thread consumer2 = new Thread(new Consumer(queue));
        Thread producer = new Thread(new Producer(queue));
        Thread producer1 = new Thread(new Producer(queue));
        Thread producer2 = new Thread(new Producer(queue));
        consumer.start();
        consumer1.start();
        consumer2.start();
        producer.start();
        producer1.start();
        producer2.start();
        consumer.join();
        consumer1.join();
        consumer2.join();
        producer.join();
        producer1.join();
        producer2.join();
        assertThat(queue.size(), is(0));
    }

    @Test
    public void waitQueueIsNotEmpty() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);
        Thread consumer = new Thread(new Consumer(queue));
        Thread producer = new Thread(new Producer(queue));
        Thread producer1 = new Thread(new Producer(queue));
        Thread producer2 = new Thread(new Producer(queue));
        Thread producer3 = new Thread(new Producer(queue));
        Thread producer4 = new Thread(new Producer(queue));
        consumer.start();
        producer.start();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        consumer.join();
        producer.join();
        producer1.join();
        producer2.join();
        producer3.join();
        producer4.join();
        assertThat(queue.size(), is(4));
    }
}