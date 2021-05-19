package ru.job4j.concurrent;

public class CountShareMain {

    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        Thread first = new Thread(count::increment);
        Thread second = new Thread(count::increment);
        Thread third = new Thread(count::increment);
        Thread fourth = new Thread(count::increment);
        Thread fifth = new Thread(count::increment);
        Thread sixth = new Thread(count::increment);
        Thread seventh = new Thread(count::increment);
        Thread eighth = new Thread(count::increment);
        Thread nineth = new Thread(count::increment);
        Thread tenth = new Thread(count::increment);
        first.start();
        second.start();
        third.start();
        fourth.start();
        fifth.start();
        sixth.start();
        seventh.start();
        eighth.start();
        nineth.start();
        tenth.start();
        first.join();
        second.join();
        third.join();
        fourth.join();
        fifth.join();
        sixth.join();
        seventh.join();
        eighth.join();
        nineth.join();
        tenth.join();
        System.out.println(count.getValue());
    }
}
