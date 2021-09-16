package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);
    private int taskCount = 0;

    /**
     * Инициализируем нити по количеству ядер в системе, каждая нить
     * достает задачу из блокирующей очереди и выполняет её метод run().
     * Сразу запускаем нити в работу.
     */
    public ThreadPool() {
        int cores = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < cores; i++) {
            threads.add(new Thread(() -> {
                while (tasks.size() != 0 || !Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        tasks.poll().run();
                        System.out.println("taskCount" + taskCount++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.currentThread().getName() + "в интерапте");
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
        for (Thread thread: threads) {
            thread.start();
        }
    }

    /**
     * Добавляем задачи не параллельно.
     */
    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    /**
     * Клиент выставит флаг прерывания в тот момент, когда ему это понадобится.
     */
    public void shutDown() {
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }

    /**
     * Инициализируем пул, запускаются нити и ждут задач для выполнения.
     * После того, как задачи кончились запускаем shutDown, который в свою очередь
     * выставляет флаг прерывания, но задачи всё равно будут выполнены все, пока
     * tasks != 0.
     */
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 10000; i++) {
            try {
                pool.work(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(this);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutDown();
    }
}
