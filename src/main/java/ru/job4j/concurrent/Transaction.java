package ru.job4j.concurrent;

public class Transaction extends Thread {

    private final Account account;
    private int withDraw;

    public Transaction(Account account, int withDraw) {
        this.account = account;
        this.withDraw = withDraw;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(System.currentTimeMillis() % 500);
        } catch (InterruptedException e) {
            return;
        }

        synchronized (account) {
            int total = account.getMoney();
            System.out.println("Current amount = " + account.getMoney());
            if (total >= withDraw) {
                account.setMoney(total - withDraw);
            }
        }
    }
}

class Account {
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }
}
