package com.db;

public class ConsoleSaver implements Saver {
    /**
     * log message to console
     * @param message
     */
    public void log(String message) {
        System.out.print(message);
    }
}
