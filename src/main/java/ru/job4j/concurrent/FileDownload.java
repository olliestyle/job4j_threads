package ru.job4j.concurrent;

import java.io.*;
import java.net.URL;

public class FileDownload {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("fileDownload.txt"));
        String file = br.readLine();
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int i = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                System.out.println(i++);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
