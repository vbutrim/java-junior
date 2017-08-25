package com.db;

public class Message {
    static private String previousString = "";
    static private int countPrevString = 0;
    static private boolean toInput = false;
    static private int currentSum = 0;

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
            msg += (new Format((Character) message).getMessageToLog());
        } else if (message instanceof Integer) {
            if (currentSum != 0 && currentSum + (int) message < Integer.MIN_VALUE + (int) message - 2) {
                msg += flushInt();
            }
            currentSum += (int) message;
            toInput = true;
        } else if (message instanceof Boolean) {
            msg += (new Format((Boolean) message).getMessageToLog());
        } else if (message instanceof Byte) {
            msg += (new Format((int) (Byte) message).getMessageToLog());
        } else if (message instanceof int[]) {
            msg += (new Format((int[]) message).getMessageToLog());
        } else {
            msg += (new Format(message).getMessageToLog());
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
            msg = new Format(currentSum).getMessageToLog();
            toInput = false;
            currentSum = 0;
        }

        return msg;
    }

    private String flushString() {
        String msg = "";

        if (countPrevString != 0 && !"".equals(previousString)) {
            if (countPrevString > 1) {
                msg = (new Format(previousString + " (x" + countPrevString + ")").getMessageToLog());
            } else {
                msg = (new Format(previousString).getMessageToLog());
            }
            resetStringState();
        }

        return msg;
    }

    public String flush() {
        return flushInt() + flushString();
    }
}
