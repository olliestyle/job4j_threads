package ru.job4j.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserCacheTask {
        private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
        private final AtomicInteger id = new AtomicInteger();

        public void add(User user) {
            users.put(id.incrementAndGet(), User.of(user.getName()));
        }

        public User findById(int id) {
            return User.of(users.get(id).getName());
        }

        public List<User> findAll() {
            List<User> list = new ArrayList<>();
            for (User u: users.values()) {
                list.add(User.of(u.getName()));
            }
            return list;
        }
}
