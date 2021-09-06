package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, SimpleUser> store = new HashMap<>();

    public synchronized boolean add(SimpleUser user) {
        boolean isAdd = false;
        if (store.put(user.getId(), user) == null) {
            isAdd = true;
        }
        return isAdd;
    }

    public synchronized boolean update(SimpleUser user) {
        boolean isUpdate = false;
        if (store.containsKey(user.getId())) {
            store.put(user.getId(), user);
            isUpdate = true;
        }
        return isUpdate;
    }

    public synchronized boolean delete(SimpleUser user) {
        boolean isDelete = false;
        if (store.remove(user.getId()) != null) {
            isDelete = true;
        }
        return isDelete;
    }

    private SimpleUser getUserById(int id) {
        return store.get(id);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        SimpleUser fromUser = getUserById(fromId);
        SimpleUser toUser = getUserById(toId);
        if (fromUser != null && toUser != null) {
            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() + amount);
        }
    }

    @Override
    public String toString() {
        return "UserStorage{" + "store=" + store + '}';
    }
}

class SimpleUser {
    private int id;
    private int amount;

    public SimpleUser(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "SimpleUser{" + "id=" + id + ", amount=" + amount + '}';
    }
}
