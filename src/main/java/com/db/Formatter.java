package com.db;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.System.lineSeparator;

public class Formatter {
    public Formatter() {
    }

    public String formatMessage(Object message, String prefix) {
        return prefix + ": " + message + lineSeparator();
    }

}
