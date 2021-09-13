package ru.job4j.concurrent;

import java.util.concurrent.atomic.AtomicReference;

public class CASStack<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        CASStack<Integer> casStack = new CASStack<>();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println("Pushed " + i);
                casStack.push(i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println("Polled " + casStack.poll());
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        Node<T> ref;
        do {
            ref = head.get();
            temp.next = ref;
        } while (!head.compareAndSet(ref, temp));
    }

    public T poll() {
        Node<T> ref;
        Node<T> temp;
        do {
            ref = head.get();
            if (ref == null) {
                throw new IllegalStateException("Stack is empty");
            }
            temp = ref.next;
        } while (!head.compareAndSet(ref, temp));
        ref.next = null;
        return ref.value;
    }

    private static final class Node<T> {
        final T value;

        Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }
}
