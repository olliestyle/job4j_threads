package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            String[] symbols = {"\\", "|", "/"};
            try {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(500);
                    System.out.print("\rLoading : " + i + "% " + symbols[i % 3]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
    }
}
