package ru.job4j.concurrent.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FindIndex<T> extends RecursiveAction {

    private final T[] array;
    private final T toFind;
    private int index;
    private int from;
    private int to;

    public FindIndex(T[] array, T toFind, int fromIndex, int toIndex) {
        this.array = array;
        this.toFind = toFind;
        this.from = fromIndex;
        this.to = toIndex;
    }

    @Override
    protected void compute() {
        if (to - from < 10) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(toFind)) {
                    index = i;
                    break;
                }
            }
            return;
        }
        int mid = (to + from) / 2;
        FindIndex<T> leftSearch = new FindIndex<>(array, toFind, from, mid);
        FindIndex<T> rightSearch = new FindIndex<>(array, toFind, mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        leftSearch.join();
        rightSearch.join();
        System.out.println(leftSearch.index);
        System.out.println(rightSearch.index);
    }

    public int findIndex() {
        FindIndex<T> fi = new FindIndex<>(array, toFind, 0, array.length);
        ForkJoinPool.commonPool().invoke(fi);
        return fi.index;
    }

    public static void main(String[] args) {
        String[] strings = {"aaa", "bbb", "ccc"};
        FindIndex<String> stringFindIndex = new FindIndex<>(strings, "bbb", 0, strings.length);
        int index = stringFindIndex.findIndex();
        System.out.println(index);
        String[] stringsBig = {"aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii",
                               "jjj", "kkk", "lll", "mmm", "nnn", "ooo", "ppp", "qqq", "rrr",
                               "sss", "ttt", "uuu", "vvv", "www", "xxx", "yyy", "zzz", "111",
                               "222", "333", "444", "555", "666", "777", "888", "999"};
        stringFindIndex = new FindIndex<>(stringsBig, "999", 0, stringsBig.length);
        index = stringFindIndex.findIndex();
        System.out.println(index);
    }
}
