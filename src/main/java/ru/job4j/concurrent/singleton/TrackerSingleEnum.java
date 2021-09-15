package ru.job4j.concurrent.singleton;

/**
 * Реализация с применением enum, аналогична однопоточной реализации.
 */

public enum TrackerSingleEnum {
    INSTANCE;

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
    }
}
