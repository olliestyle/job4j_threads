package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void waitQueueIsEmpty() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread consumer = new Thread(new Consumer(queue));
        Thread producer = new Thread(new Producer(queue));
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        assertThat(queue.size(), is(0));
    }

    @Test
    public void waitQueueIsNotEmpty() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread consumer = new Thread(new Consumer(queue));
        Thread producer = new Thread(new Producer(queue));
        Thread producer1 = new Thread(new Producer(queue));
        consumer.start();
        producer.start();
        producer1.start();
        consumer.join();
        producer.join();
        producer1.join();
        assertThat(queue.size(), is(1));
    }
}