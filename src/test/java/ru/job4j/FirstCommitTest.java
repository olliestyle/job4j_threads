package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class FirstCommitTest {
    @Test
    public void test() {
        FirstCommit firstCommit = new FirstCommit();
        assertTrue(firstCommit.firstCommit(true));
        assertFalse(firstCommit.firstCommit(false));
    }
}