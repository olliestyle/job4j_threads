package ru.job4j.concurrent;

public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            Thread.sleep(500);
                            queue.offer(index);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        );
        producer.start();
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        try {
                            if (queue.size() == 0 && producer.getState() == Thread.State.TERMINATED) {
                                return;
                            }
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
    }
}
