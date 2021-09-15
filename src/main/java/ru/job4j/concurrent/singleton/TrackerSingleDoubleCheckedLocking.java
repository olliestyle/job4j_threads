package ru.job4j.concurrent.singleton;

/**
 * Double checked locking
 */

public class TrackerSingleDoubleCheckedLocking {
    private static volatile TrackerSingleDoubleCheckedLocking instance;

    private TrackerSingleDoubleCheckedLocking() {
    }

    public static TrackerSingleDoubleCheckedLocking getInstance() {
        if (instance == null) {
            synchronized (TrackerSingleDoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new TrackerSingleDoubleCheckedLocking();
                }
            }
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingleDoubleCheckedLocking tracker = TrackerSingleDoubleCheckedLocking.getInstance();
    }
}
