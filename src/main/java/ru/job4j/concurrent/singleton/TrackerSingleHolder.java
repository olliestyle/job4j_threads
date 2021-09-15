package ru.job4j.concurrent.singleton;

/**
 * Если у вас нет необходимости в ленивой загрузке, то используйте шаблоны из первой группы.
 * Например, инициализация кэша или базы данных.
 * Если в приложении есть затратные ресурсы нужно использовать шаблоны с ленивой загрузкой.
 * Здесь можно использовать только один шаблон - это Holder.
 * Другие шаблоны будут отрицательно влиять на производительность системы.
 */

public class TrackerSingleHolder {
    private TrackerSingleHolder() {
    }

    public static TrackerSingleHolder getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static final class Holder {
        private static final TrackerSingleHolder INSTANCE = new TrackerSingleHolder();
    }

    public static void main(String[] args) {
        TrackerSingleHolder tracker = TrackerSingleHolder.getInstance();
    }
}
