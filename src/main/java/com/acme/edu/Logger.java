package com.acme.edu;

public class Logger {
    public static void log(Object message) {
        if (message instanceof String) {
            System.out.print("string: ");
        } else if (message instanceof Character) {
            System.out.print("char: ");
        } else if (message instanceof Number ||
                   message instanceof Boolean) {
            System.out.print("primitive: ");
        } else
            System.out.print("reference: ");

        System.out.print(message + "\n");
    }
}
