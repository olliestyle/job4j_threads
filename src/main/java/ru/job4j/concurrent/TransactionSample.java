package ru.job4j.concurrent;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionSample {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000);

        Transaction[] transactions = {
                new Transaction(account, 500),
                new Transaction(account, 100),
                new Transaction(account, 200),
                new Transaction(account, 150),
                new Transaction(account, 50)
                };

        for (Transaction tr: transactions) {
            tr.start();
        }

        for (Transaction tr: transactions) {
            tr.join();
        }

        System.out.println("Total left money: " + account.getMoney());
    }
}
