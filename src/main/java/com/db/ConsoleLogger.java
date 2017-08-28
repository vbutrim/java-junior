package com.db;

public class ConsoleLogger implements Logger {
    public void log(String message) {
        System.out.print(message);
    }
}
