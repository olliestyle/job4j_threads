package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final List<SimpleUser> store = new ArrayList<>();

    public synchronized boolean add(SimpleUser user) {
        return store.add(user);
    }

    public boolean update(SimpleUser user) {
        return true;
    }

    public synchronized boolean delete(SimpleUser user) {
        return store.remove(user);
    }

    private SimpleUser getUserById(int id) {
        for (SimpleUser user: store) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        SimpleUser fromUser = getUserById(fromId);
        SimpleUser toUser = getUserById(toId);
        if (fromUser == null) {
            System.out.println("User with id "  + fromId + " not found");
        } else if (toUser == null)  {
            System.out.println("User with id "  + toId + " not found");
        } else {
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
