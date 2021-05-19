package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WgetConsole implements Runnable {

    private final String url;
    private final int speed;
    private final String fileName;

    public WgetConsole(String url, String fileName, int speed) {
        this.url = url;
        this.fileName = fileName;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int i = 0;
            long start = System.currentTimeMillis();
            while ((bytesRead = bis.read(dataBuffer, 0, 1024)) != -1) {
                long end = System.currentTimeMillis();
                long download = end - start;
                if (download < speed) {
                    System.out.println("Thread will sleep addition " + (speed - download) + " mlsec");
                    Thread.sleep(speed - download);
                }
                fos.write(dataBuffer, 0, bytesRead);
                start = System.currentTimeMillis();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 3) {
            String url = args[0];
            String fileName = args[1];
            int speed = Integer.parseInt(args[2]);
            Thread wget = new Thread(new WgetConsole(url, fileName,speed));
            wget.start();
            wget.join();
        } else {
            System.out.println("Please, enter correct arguments: url, fileName, speed");
        }
    }
}
