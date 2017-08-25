package com.db;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.System.lineSeparator;

/**
 * Created by Java_16 on 25.08.2017.
 */
public class Format {
    private String messageToLog;

    public Format(Object message) {
        messageToLog = "reference: " + message + lineSeparator();
    }

    public Format(int message) {
        messageToLog = "primitive: " + message + lineSeparator();
    }

    public Format(Character message) {
        messageToLog = "char: " + message + lineSeparator();
    }

    public Format(Boolean message) {
        messageToLog = "primitive: " + message + lineSeparator();
    }

    public Format(String message) {
        messageToLog = "string: " + message + lineSeparator();
    }

    public Format(int[] message) {
        StringBuilder result = new StringBuilder("primitives array: {");
        int lengthMas = (message).length;

        for (int i = 0; i < lengthMas - 1; ++i) {
            result.append(message[i] + ", ");
        }
        result.append(message[lengthMas - 1] + "}" + lineSeparator());
        messageToLog = result.toString();
    }

    public String getMessageToLog() {
        return messageToLog;
    }

}
