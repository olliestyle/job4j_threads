package ru.job4j.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *      Если мы добавляем локальный объект в общее хранилище,
 *      то ссылка на непотокобезопасный объект становится доступна всем.
 *      Т.е. если одна нить добавила локального для неё User@111, то в общем ресурсе users мы из другой нити можем получить
 *      доступ к ссылке User@111 и поменять поля в нём
 */

public class UserCacheNotThreadSafe {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();


    public void add(User user) {
        users.put(id.incrementAndGet(), user);
    }

    public User findById(int id) {
        return users.get(id);
    }
}
