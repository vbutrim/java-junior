package com.db;

/*
public class FormattingSavingHandler {
    static protected String previousString = "";
    static protected int countPrevString = 0;
    static protected boolean toInput = false;
    static protected int currentSum = 0;

    static protected String msg;

    static protected Formatter stringFormatter = new Formatter();

    public FormattingSavingHandler() {

    }

    public String packMessage(Object message) {
        msg = "";
        if (!(message instanceof Integer)) {
            msg += flushInt();
        }

        if (!(message instanceof String)) {
            msg += flushString();
        }

        return "";
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
*/
