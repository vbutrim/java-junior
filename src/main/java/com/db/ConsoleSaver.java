package com.db;

public class ConsoleSaver implements Saver {
    /**
     * log message to console
     * @param message
     */
    @Override
    public void log(String message) {
        System.out.print(message);
    }
}
