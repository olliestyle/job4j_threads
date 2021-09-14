package ru.job4j.concurrent.nonblocking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CacheTest {
    @Test
    public void whenAddAndUpdate() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        assertTrue(cache.add(base));
        Base updateBase = new Base(1, 0);
        updateBase.setName("AAA");
        assertTrue(cache.update(updateBase));
    }

    @Test(expected = OptimisticException.class)
    public void whenOptimisticException() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        assertTrue(cache.add(base));
        Base updateBase = new Base(1, 1);
        updateBase.setName("AAA");
        assertFalse(cache.update(updateBase));
    }
}