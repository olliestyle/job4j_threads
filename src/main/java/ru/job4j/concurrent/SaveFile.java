package ru.job4j.concurrent;

import java.io.*;

public class SaveFile {
    public synchronized void saveContent(String content, File file) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                bos.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File fileToParse = new File("helloParse.txt");
        File fileToSave = new File("helloSave.txt");
        File fileToSaveWithoutUnicode = new File("helloSaveWithoutUnicode.txt");
        new SaveFile().saveContent(new ParseFile(fileToParse).getContentByPredicate(elem -> true), fileToSave);
        new SaveFile().saveContent(new ParseFile(fileToParse).getContentByPredicate(elem -> elem < 0x80), fileToSaveWithoutUnicode);
    }
}
