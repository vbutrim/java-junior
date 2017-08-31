package com.db;

import static java.lang.System.lineSeparator;

public class Formatter {
    /**
     * format message with prefix
     * @param message
     * @param prefix
     * @return
     */
    public String formatMessage(Object message, String prefix) {
        return prefix + ": " + message + lineSeparator();
    }
}