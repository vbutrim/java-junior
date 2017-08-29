package com.acme.edu;

import com.db.OldMessage;

public class Logger {
    static private OldMessage serviceMessage = new OldMessage();
    static private com.db.Logger serviceLogger = new com.db.ConsoleLogger();

    public static void log(Object message) {
        serviceLogger.log(typeAnalysis(message));
    }

    public static String typeAnalysis(Object message) {
        /*
        packMessage from OldMessage.java should be here
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


