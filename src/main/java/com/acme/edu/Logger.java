package com.acme.edu;

import java.util.Objects;

import static java.lang.System.lineSeparator;

public class Logger {
    static private String previousString = "";
    static private int countPrevString = 0;

    static private boolean toInput = false;
    static private int currentSum = 0;

    public static void log(Object message) {
        if (toInput && !(message instanceof Integer)) {
            System.out.print("primitive: " + currentSum + lineSeparator());
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
            System.out.print("char: ");
        } else if (message instanceof Integer) {
            if (currentSum != 0 && currentSum + (int) message < Integer.MIN_VALUE + (int) message - 2) {
                System.out.print("primitive: " + currentSum + lineSeparator());
                toInput = false;
                currentSum = 0;
            }
            currentSum += (int) message;
            toInput = true;

            countPrevString = 0;
            previousString = "";

            return;
        } else if (message instanceof Byte ||
                message instanceof Boolean) {
            System.out.print("primitive: ");
        } else if (message instanceof int[]) {
            System.out.print("primitives array: {");
            int lengthMas = ((int[]) message).length;

            for (int i = 0; i < lengthMas - 1; ++i) {
                System.out.print(((int[]) message)[i] + ", ");
            }
            System.out.print(((int[]) message)[lengthMas - 1] + "}" + lineSeparator());
            return;
        } else {
            System.out.print("reference: ");
        }

        System.out.print(message + lineSeparator());

        countPrevString = 0;
        previousString = "";
    }


    public static  void flush() {
        flushInt();
        flushString();
    }

    public static void flushInt() {
        if (toInput) {
            System.out.print("primitive: " + currentSum + lineSeparator());
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
            countPrevString = 0;
            previousString = "";
        }
    }
}


