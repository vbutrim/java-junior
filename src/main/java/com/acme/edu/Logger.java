package com.acme.edu;

import com.db.Message;

public class Logger {
    static private Message serviceMessage = new Message();
    static private com.db.Logger serviceLogger = new com.db.ConsoleLogger();

    public static void log(Object message) {
        serviceLogger.log(serviceMessage.packMessage(message));
    }

    /**
     * finalize work with logger at all
     */
    public static void flush() {
        serviceLogger.log(serviceMessage.flush());
    }
}


