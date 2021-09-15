package ru.job4j.concurrent.singleton;

/**
 * Lazy initialisation
 * Single checked locking.
 */

public class TrackerSingleCheckedLocking {
    private static TrackerSingleCheckedLocking instance;

    private TrackerSingleCheckedLocking() {
    }

    public static synchronized TrackerSingleCheckedLocking getInstance() {
        if (instance == null) {
            instance = new TrackerSingleCheckedLocking();
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingleCheckedLocking tracker = TrackerSingleCheckedLocking.getInstance();
    }
}
