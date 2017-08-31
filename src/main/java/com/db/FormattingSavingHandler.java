package com.db;

public class FormattingSavingHandler implements EventHandler{
    /**
     * general handler of Object
     * @param message
     * @return String format
     */
    public String packMessage(Object message) {
        if (message instanceof FlushTrigger) {
            return flush();
        }

        msg = "";
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

    /**
     * clear buffer
     * @return
     */
    public String flush() {
        return flushInt() + flushString();
    }

    /**
     * general func
     * @param message
     */
    @Override
    public void handleEvent(Object message) {
        serviceSave.log(packMessage(message));
    }

    static private final String PRIMITIVEPREFIX = "primitive";
    static private final String STRINGPREFIX = "string";
    static private final String OBJECTPREFIX = "reference";
    static private final String INTARRAYPREFIX = "primitives array";
    static private final String CHARPREFIX = "char";

    static private String previousString = "";
    static private int countPrevString = 0;
    static private boolean toInput = false;
    static private int currentSum = 0;

    static private String msg;

    static private Formatter stringFormatter = new Formatter();
    static private Saver serviceSave = new ConsoleSaver();

    /**
     * Specific handler for char
     * @param message
     * @return
     */
    private String packCharacterMessage(Character message) {
        return stringFormatter.formatMessage(message, CHARPREFIX);
    }

    /**
     * Specific handler for boolean
     * @param message
     * @return
     */
    private String packBooleanMessage(Boolean message) {
        return stringFormatter.formatMessage(message, PRIMITIVEPREFIX);
    }

    /**
     * Specific handler for byte
     * @param message
     * @return
     */
    private String packByteMessage(Byte message) {
        return stringFormatter.formatMessage(message, PRIMITIVEPREFIX);
    }

    /**
     * Specific handler for String
     * @param message
     * @return
     */
    private String packStringMessage(String message) {
        String msgLocal = "";
        String[] args = (message).split(" ");
        if ("str".equals(args[0])) {
            message = args[1];
        }

        if (!previousString.equals(message)) {
            msgLocal += flushString();
            previousString = message;
        }
        ++countPrevString;

        return msgLocal;
    }

    /**
     * Specific handler for Int
     * @param message
     * @return
     */
    private String packIntMessage(Integer message) {
        String msgLocal = "";
        if (currentSum != 0 && !(currentSum < Integer.MAX_VALUE - message)) {
            msgLocal += flushInt();
        }
        currentSum += message;
        toInput = true;

        return msgLocal;
    }

    /**
     * Specific handler for Int array
     * @param message
     * @return
     */
    private String packIntArrayMessage(int[] message) {
        String msgLocal;
        StringBuilder result = new StringBuilder("{");

        int lengthMas = message.length;

        for (int i = 0; i < lengthMas - 1; ++i) {
            result.append(message[i] + ", ");
        }
        result.append(message[lengthMas - 1] + "}");
        msgLocal = result.toString();

        return stringFormatter.formatMessage(msgLocal, INTARRAYPREFIX);
    }

    /**
     * Common handler for other objects
     * @param message
     * @return
     */
    private String packObjectMessage(Object message) {
        return stringFormatter.formatMessage(message, OBJECTPREFIX);
    }

    /**
     * make null fields
     */
    private void resetStringState() {
        countPrevString = 0;
        previousString = "";
    }

    /**
     * clear buffer int
     * @return
     */
    private String flushInt() {
        String msgLocal = "";
        if (toInput) {
            msgLocal = stringFormatter.formatMessage(currentSum, PRIMITIVEPREFIX);
            toInput = false;
            currentSum = 0;
        }

        return msgLocal;
    }

    /**
     * clear buffer string
     * @return
     */
    private String flushString() {
        String msgLocal = "";

        if (countPrevString != 0 && !"".equals(previousString)) {
            if (countPrevString > 1) {
                msgLocal = stringFormatter.formatMessage(previousString + " (x" + countPrevString + ")", "string");
            } else {
                msgLocal = stringFormatter.formatMessage(previousString, STRINGPREFIX);
            }
            resetStringState();
        }

        return msgLocal;
    }
}
