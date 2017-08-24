package com.acme.edu;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static java.lang.System.lineSeparator;

public class Logger {
    static private String previousString = "";
    static private int countPrevString = 0;
    static private boolean toInput = false;
    static private int currentSum = 0;

    @NotNull
    static private String format(Object message) {
        StringBuilder result = new StringBuilder("reference: ");
        result.append(message + lineSeparator());
        return result.toString();
    }

    @NotNull
    static private String format(int message) {
        StringBuilder result = new StringBuilder("primitive: ");
        result.append(message + lineSeparator());
        return result.toString();
    }

    @NotNull
    static private String format(Character message) {
        StringBuilder result = new StringBuilder("char: ");
        result.append(message + lineSeparator());
        return result.toString();
    }

    @NotNull
    static private String format(Boolean message) {
        StringBuilder result = new StringBuilder("primitive: ");
        result.append(message + lineSeparator());
        return result.toString();
    }

    @NotNull
    static private String format(int[] message) {
        StringBuilder result = new StringBuilder("primitives array: {");
        int lengthMas = (message).length;

        for (int i = 0; i < lengthMas - 1; ++i) {
            result.append(message[i] + ", ");
        }
        result.append(message[lengthMas - 1] + "}" + lineSeparator());
        return result.toString();
    }

    public static void resetStringState() {
        countPrevString = 0;
        previousString = "";
    }

    public static void log(Object message) {
        if (toInput && !(message instanceof Integer)) {
            System.out.print(format(currentSum));
            toInput = false;
            currentSum = 0;
        }

        if (!(message instanceof String)) {
            flushString();
        }

        if (message instanceof String) {
            String[] args = ((String) message).split(" ");
            if ("str".equals(args[0])) {
                message = args[1];
            }

            if (!previousString.equals((String) message)) {
                flushString();
                previousString = (String) message;
            }
            ++countPrevString;

            return;
        } else if (message instanceof Character) {
            System.out.print(format((Character)message));
        } else if (message instanceof Integer) {
            if (currentSum != 0 && currentSum + (int) message < Integer.MIN_VALUE + (int) message - 2) {
                flushInt();
            }
            currentSum += (int) message;
            toInput = true;
        } else if (message instanceof Boolean) {
            System.out.print(format((Boolean) message));
        } else if (message instanceof Byte) {
            System.out.print(format((int)(Byte)message));
        } else if (message instanceof int[]) {
            System.out.print(format((int[]) message));
        } else {
            System.out.print(format(message));
        }

        resetStringState();
    }

    public static void flush() {
        flushInt();
        flushString();
    }

    public static void flushInt() {
        if (toInput) {
            System.out.print(format(currentSum));
            toInput = false;
            currentSum = 0;
        }
    }

    public static void flushString() {
        if (countPrevString != 0 && !"".equals(previousString)) {
            System.out.print("string: " + previousString);
            if (countPrevString > 1) {
                System.out.print(" (x" + countPrevString + ")");
            }
            System.out.print(lineSeparator());

            resetStringState();
        }
    }
}


