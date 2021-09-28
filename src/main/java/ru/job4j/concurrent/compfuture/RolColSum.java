package ru.job4j.concurrent.compfuture;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        public int getRowSum() {
            return this.rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum
                    && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{" + "rowSum=" + rowSum + ", colSum=" + colSum + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] res = new Sums[matrix.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = new Sums();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                res[i].rowSum += matrix[i][j];
                res[j].colSum += matrix[i][j];
            }
        }
        return res;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] res = new Sums[matrix.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = new Sums();
        }
        for (int i = 0; i < matrix.length; i++) {
            runTask(matrix, res, i);
        }
        return res;
    }

    public static CompletableFuture<Void> runTask(int[][] matrix, Sums[] res, int colRow) {
        return CompletableFuture.runAsync(() -> {
            for (int i = 0; i < matrix.length; i++) {
                res[colRow].rowSum += matrix[colRow][i];
            }
            for (int i = 0; i < matrix.length; i++) {
                res[colRow].colSum += matrix[i][colRow];
            }
        });
    }
}