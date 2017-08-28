package com.db;

import static java.lang.System.lineSeparator;

public class Message {
    static private String previousString = "";
    static private int countPrevString = 0;
    static private boolean toInput = false;
    static private int currentSum = 0;

    Formatter stringFormatter = new Formatter();

    public Message() {

    }

    public String packMessage(Object message) {
        String msg = "";
        if (!(message instanceof Integer)) {
            msg += flushInt();
        }

        if (!(message instanceof String)) {
            msg += flushString();
        }

        if (message instanceof String) {
            String[] args = ((String) message).split(" ");
            if ("str".equals(args[0])) {
                message = args[1];
            }

            if (!previousString.equals((String) message)) {
                msg += flushString();
                previousString = (String) message;
            }
            ++countPrevString;

            return msg;
        } else if (message instanceof Character) {
            msg += (stringFormatter.formatMessage(message, "char"));
        } else if (message instanceof Integer) {
            if (currentSum != 0 && currentSum + (int) message < Integer.MIN_VALUE + (int) message - 2) {
                msg += flushInt();
            }
            currentSum += (int) message;
            toInput = true;
        } else if (message instanceof Boolean) {
            msg += (stringFormatter.formatMessage(message, "primitive"));
        } else if (message instanceof Byte) {
            msg += (stringFormatter.formatMessage(message, "primitive"));
        } else if (message instanceof int[]) {
            String messageToLog;
            StringBuilder result = new StringBuilder("{");

            int lengthMas = ((int[]) message).length;

            for (int i = 0; i < lengthMas - 1; ++i) {
                result.append(((int[])message)[i] + ", ");
            }
            result.append(((int[])message)[lengthMas - 1] + "}");
            messageToLog = result.toString();

            msg += (stringFormatter.formatMessage(messageToLog, "primitives array"));
        } else {
            msg += (stringFormatter.formatMessage(message, "reference"));
        }

        resetStringState();

        return msg;
    }

    private void resetStringState() {
        countPrevString = 0;
        previousString = "";
    }

    private String flushInt() {
        String msg = "";
        if (toInput) {
            msg = stringFormatter.formatMessage(currentSum, "primitive");
            toInput = false;
            currentSum = 0;
        }

        return msg;
    }

    private String flushString() {
        String msg = "";

        if (countPrevString != 0 && !"".equals(previousString)) {
            if (countPrevString > 1) {
                msg = (stringFormatter.formatMessage(previousString + " (x" + countPrevString + ")", "string"));
            } else {
                msg = (stringFormatter.formatMessage(previousString, "string"));
            }
            resetStringState();
        }

        return msg;
    }

    public String flush() {
        return flushInt() + flushString();
    }
}
