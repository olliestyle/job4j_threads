package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CountJCIPTest {

    private class ThreadCount extends Thread {
        private final CountJCIP count;

        private ThreadCount(final CountJCIP count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecuteTwoThreadThen2() throws InterruptedException {
        final CountJCIP count = new CountJCIP();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }
}