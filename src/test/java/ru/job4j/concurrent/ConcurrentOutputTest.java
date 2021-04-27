package ru.job4j.concurrent;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ConcurrentOutputTest {
    @Test
    public void test() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream defaultOut = System.out;
        System.setOut(new PrintStream(baos));
        ConcurrentOutput concurrentOutput = new ConcurrentOutput();
        concurrentOutput.threadTest();
        assertTrue(new String(baos.toByteArray()).contains("main\n"));
//        assertTrue(new String(baos.toByteArray()).contains("Thread-0\n"));
//        assertTrue(new String(baos.toByteArray()).contains("Thread-1\n"));
        System.setOut(defaultOut);
    }
}