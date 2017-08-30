package com.db;

public class ConsoleSaver implements Saver {
    public void log(String message) {
        System.out.print(message);
    }
}
