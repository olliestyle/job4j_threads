package ru.job4j.concurrent.compfuture;

import org.junit.Test;

import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void whenConsistent() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums s1 = new RolColSum.Sums();
        RolColSum.Sums s2 = new RolColSum.Sums();
        RolColSum.Sums s3 = new RolColSum.Sums();
        s1.setRowSum(6);
        s1.setColSum(12);
        s2.setRowSum(15);
        s2.setColSum(15);
        s3.setRowSum(24);
        s3.setColSum(18);
        RolColSum.Sums[] expected = {s1, s2, s3};
        assertEquals(expected, RolColSum.sum(matrix));
    }

    @Test
    public void whenAsync() throws InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums s1 = new RolColSum.Sums();
        RolColSum.Sums s2 = new RolColSum.Sums();
        RolColSum.Sums s3 = new RolColSum.Sums();
        s1.setRowSum(6);
        s1.setColSum(12);
        s2.setRowSum(15);
        s2.setColSum(15);
        s3.setRowSum(24);
        s3.setColSum(18);
        RolColSum.Sums[] expected = {s1, s2, s3};
        RolColSum.Sums[] actual = RolColSum.asyncSum(matrix);
        Thread.sleep(3000);
        assertEquals(expected, actual);
    }
}