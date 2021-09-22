package ru.job4j.concurrent.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T toFind;
    private final int from;
    private final int to;

    public FindIndex(T[] array, T toFind, int fromIndex, int toIndex) {
        this.array = array;
        this.toFind = toFind;
        this.from = fromIndex;
        this.to = toIndex;
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            int index = -1;
            for (int i = from; i <= to; i++) {
                System.out.println(i);
                if (array[i].equals(toFind)) {
                    index = i;
                    break;
                }
            }
            return index;
        }
        int mid = (to + from) / 2;
        FindIndex<T> leftSearch = new FindIndex<>(array, toFind, from, mid);
        FindIndex<T> rightSearch = new FindIndex<>(array, toFind, mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        return choice(leftSearch.join(), rightSearch.join());
    }

    private Integer choice(Integer left, Integer right) {
        return left != -1 ? left : right;
    }

    public Integer findIndex() {
        return ForkJoinPool.commonPool().invoke(this);
    }
}
