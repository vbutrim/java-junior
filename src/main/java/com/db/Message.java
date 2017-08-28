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

    public String packCharacterMessage(Character message) {
        return stringFormatter.formatMessage(message, "char");
    }

    public String packBooleanMessage(Boolean message) {
        return stringFormatter.formatMessage(message, "primitive");
    }

    public String packByteMessage(Byte message) {
        return stringFormatter.formatMessage(message, "primitive");
    }

    public String packStringMessage(String message) {
        String msgLocal = "";
        String[] args = (message).split(" ");
        if ("str".equals(args[0])) {
            message = args[1];
        }

        if (!previousString.equals((String) message)) {
            msgLocal += flushString();
            previousString = message;
        }
        ++countPrevString;

        return msgLocal;
    }

    public String packIntMessage(Integer message) {
        String msgLocal = "";
        if (currentSum != 0 && currentSum + message < Integer.MIN_VALUE + message - 2) {
            msgLocal += flushInt();
        }
        currentSum += message;
        toInput = true;

        return msgLocal;
    }

    public String packIntArrayMessage(int[] message) {
        String msgLocal;
        StringBuilder result = new StringBuilder("{");

        int lengthMas = message.length;

        for (int i = 0; i < lengthMas - 1; ++i) {
            result.append(message[i] + ", ");
        }
        result.append(message[lengthMas - 1] + "}");
        msgLocal = result.toString();

        return stringFormatter.formatMessage(msgLocal, "primitives array");
    }

    public String packObjectMessage(Object message) {
        return stringFormatter.formatMessage(message, "reference");
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
            return msg + packStringMessage((String) message);
        } else if (message instanceof Character) {
            msg += packCharacterMessage((Character) message);
        } else if (message instanceof Integer) {
            msg += packIntMessage((Integer) message);
        } else if (message instanceof Boolean) {
            msg += packBooleanMessage((Boolean) message);
        } else if (message instanceof Byte) {
            msg += packByteMessage((Byte) message);
        } else if (message instanceof int[]) {
            msg += packIntArrayMessage((int[]) message);
        } else {
            msg += packObjectMessage(message);
        }

        resetStringState();

        return msg;
    }

    private void resetStringState() {
        countPrevString = 0;
        previousString = "";
    }

    private String flushInt() {
        String msgLocal = "";
        if (toInput) {
            msgLocal = stringFormatter.formatMessage(currentSum, "primitive");
            toInput = false;
            currentSum = 0;
        }

        return msgLocal;
    }

    private String flushString() {
        String msgLocal = "";

        if (countPrevString != 0 && !"".equals(previousString)) {
            if (countPrevString > 1) {
                msgLocal = (stringFormatter.formatMessage(previousString + " (x" + countPrevString + ")", "string"));
            } else {
                msgLocal = (stringFormatter.formatMessage(previousString, "string"));
            }
            resetStringState();
        }

        return msgLocal;
    }

    public String flush() {
        return flushInt() + flushString();
    }
}
