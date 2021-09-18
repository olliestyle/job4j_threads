package ru.job4j.concurrent.pool;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private void send(String subject, String body, String email) {

    }

    public void emailTo(User user) {
        pool.submit(() -> send("Notification + " + user.getName() + " to email " + user.getEmail(),
                "Add a new event to " + user.getName(),
                user.getEmail()));
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}