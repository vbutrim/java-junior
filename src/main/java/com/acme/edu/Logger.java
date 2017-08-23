package com.acme.edu;

public class Logger {
    public static void log(Object message) {
        if (message instanceof String) {
            System.out.print("string: ");
        } else {
            System.out.print("reference: ");
        }

        System.out.println(message);
    }

    public static void log(int message) {
        System.out.println("primitive: " + message);
    }

    public static void log(boolean message) {
        System.out.println("primitive: " + message);
    }

    public static void log(char message) {
        System.out.println("char: " + message);
    }

}
