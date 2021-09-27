package ru.job4j.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *     // В этом случае нить будет работает с локальным объект user.
 *     // Изменение этого объекта не влечет изменений в самом кеше.
 *     // Методы add и findById работают с копиями объекта User.
 *     // Т.е.если одна нить добавила локального для неё User@111,
 *     // то в общий ресурс users пойдет копия этого объекта созданная через new
 */
public class UserCacheThreadSafe {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();


    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }
}
