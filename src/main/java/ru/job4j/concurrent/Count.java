package ru.job4j.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Count {

    private AtomicInteger value = new AtomicInteger();

    public void increment() {
        value.incrementAndGet();
    }

    public AtomicInteger getValue() {
        return value;
    }
}
