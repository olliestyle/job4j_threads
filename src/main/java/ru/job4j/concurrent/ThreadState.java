package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        System.out.println("first thread state is " + first.getState());
        System.out.println("second thread state is " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println("first thread state is " + first.getState());
            System.out.println("second thread state is " + second.getState());
        }
        System.out.println("Threads not run anymore");
        System.out.println("first state is - " + first.getState());
        System.out.println("second state is - " + second.getState());
        System.out.println("main state is - " + Thread.currentThread().getState());
    }
}
