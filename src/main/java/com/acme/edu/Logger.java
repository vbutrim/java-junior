package com.acme.edu;

import org.jetbrains.annotations.NotNull;
import static java.lang.System.lineSeparator;

public class Logger {
    static private String previousString = "";
    static private int countPrevString = 0;
    static private boolean toInput = false;
    static private int currentSum = 0;

    @NotNull
    static private String format(Object message) {
        return "reference: " + message + lineSeparator();
    }

    @NotNull
    static private String format(int message) {
        return "primitive: " + message + lineSeparator();
    }

    @NotNull
    static private String format(Character message) {
        return "char: " + message + lineSeparator();
    }

    @NotNull
    static private String format(Boolean message) {
        return "primitive: " + message + lineSeparator();
    }

    @NotNull
    static private String format(String message) {
        return "string: " + message + lineSeparator();
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

    private static void flushInt() {
        if (toInput) {
            System.out.print(format(currentSum));
            toInput = false;
            currentSum = 0;
        }
    }

    private static void flushString() {
        if (countPrevString != 0 && !"".equals(previousString)) {
            if (countPrevString > 1) {
                System.out.print(format(previousString + " (x" + countPrevString + ")"));
            } else {
                System.out.print(format(previousString));
            }
            resetStringState();
        }
    }

    private static void resetStringState() {
        countPrevString = 0;
        previousString = "";
    }

    public static void log(Object message) {
        if (!(message instanceof Integer)) {
            flushInt();
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

    /**
     * finalize work with logger at all
     */
    public static void flush() {
        flushInt();
        flushString();
    }
}


