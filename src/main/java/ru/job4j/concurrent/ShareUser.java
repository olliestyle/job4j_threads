package ru.job4j.concurrent;

public class ShareUser {
    public static void main(String[] args) throws InterruptedException {
        UserCacheNotThreadSafe cache = new UserCacheNotThreadSafe();
        User user = User.of("name");
        cache.add(user);
        Thread first = new Thread(
                () -> {
                    user.setName("rename");
                }
        );
        first.start();
        first.join();
        System.out.println(cache.findById(1).getName());
    }
}
