package com.acme.edu;

import com.db.Message;

public class Logger {
    static private Message serviceMessage = new Message();
    static private com.db.Logger serviceLogger = new com.db.ConsoleLogger();

    public static void log(Object message) {
        serviceLogger.log(packMessage(message));
    }

    public static String packMessage(Object message) {
        /*
        packMessage from Message.java should be here
         */
        return serviceMessage.packMessage(message);
    }

    /**
     * finalize work with logger at all
     */
    public static void flush() {
        serviceLogger.log(serviceMessage.flush());
    }
}


