package ru.job4j.concurrent.singleton;

/**
 * Реализация с применением поля final.
 */

public class TrackerSingleFinal {
    private static final TrackerSingleFinal INSTANCE = new TrackerSingleFinal();

    private TrackerSingleFinal() {
    }

    public static TrackerSingleFinal getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingleFinal tracker = TrackerSingleFinal.getInstance();
    }
}
