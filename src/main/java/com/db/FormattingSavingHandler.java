package com.db;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;

public class FormattingSavingHandler implements EventHandler {
    private static final String PRIMITIVEPREFIX = "primitive";
    private static final String STRINGPREFIX = "string";
    private static final String OBJECTPREFIX = "reference";
    private static final String INTARRAYPREFIX = "primitives array";
    private static final String CHARPREFIX = "char";

    private static String previousString = "";
    private static int countPrevString = 0;
    private static boolean toInput = false;
    private static int currentSum = 0;
    private static boolean toInputByte = false;
    private static byte currentSumByte = 0;

    private static String msg;

    private Formatter stringFormatter = new Formatter();
    private Saver serviceSave = new ConsoleSaver();

    /**
     * general handler of Object
     *
     * @param message
     * @return String format
     */
    public String packMessage(Object message) throws IOException {
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

        if (!(message instanceof Byte)) {
            msg += flushByte();
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
     *
     * @return
     */
    public String flush() throws IOException {
        return flushInt() + flushByte() + flushString();
    }

    /**
     * general func
     *
     * @param message
     */
    @Override
    public void handleEvent(Object message) throws IOException {
        serviceSave.log(packMessage(message));
    }

    /**
     * Specific handler for char
     *
     * @param message
     * @return
     */
    private String packCharacterMessage(Character message) throws IOException{
        return stringFormatter.formatMessage(message, CHARPREFIX);
    }

    /**
     * Specific handler for boolean
     *
     * @param message
     * @return
     */
    private String packBooleanMessage(Boolean message) throws IOException {
        return stringFormatter.formatMessage(message, PRIMITIVEPREFIX);
    }

    /**
     * Specific handler for byte
     *
     * @param message
     * @return
     */
    private String packByteMessage(Byte message) throws IOException {
        String msgLocal = "";
        if (currentSumByte != 0 && !(currentSumByte < Byte.MAX_VALUE - message)) {
            msgLocal += flushByte();
        }
        currentSumByte += message;
        toInputByte = true;

        return msgLocal;
    }

    /**
     * Specific handler for String
     *
     * @param message
     * @return
     */
    private String packStringMessage(String message) throws IOException {
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
     *
     * @param message
     * @return
     */
    private String packIntMessage(Integer message) throws IOException {
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
     *
     * @param message
     * @return
     */
    private String packIntArrayMessage(int[] message) throws IOException {
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
     *
     * @param message
     * @return
     */
    private String packObjectMessage(Object message) throws IOException {
        return stringFormatter.formatMessage(message, OBJECTPREFIX);
    }

    /**
     * make null fields
     */
    private void resetStringState() throws IOException{
        countPrevString = 0;
        previousString = "";
    }

    /**
     * clear buffer int
     *
     * @return
     */
    private String flushInt() throws IOException {
        String msgLocal = "";
        if (toInput) {
            msgLocal = stringFormatter.formatMessage(currentSum, PRIMITIVEPREFIX);
            toInput = false;
            currentSum = 0;
        }

        return msgLocal;
    }

    /**
     * byte insert is ended
     * @return
     * @throws IOException
     */
    private String flushByte() throws IOException {
        String msgLocal = "";
        if (toInputByte) {
            msgLocal = stringFormatter.formatMessage(currentSumByte, PRIMITIVEPREFIX);
            toInputByte = false;
            currentSumByte = 0;
        }

        return msgLocal;
    }

    /**
     * clear buffer string
     *
     * @return
     */
    private String flushString() throws IOException {
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
