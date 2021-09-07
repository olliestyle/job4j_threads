package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    // Берем итератор от списка, который скопировали на момент создания итератора.
    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private List<T> copy(List<T> listToCopy) {
        return listToCopy.stream().collect(Collectors.toList());
    }
}
