package com.acme.edu;

import com.db.ConsoleSaver;
import com.db.Message;
import com.db.Saver;

public class Logger {
    static private Message serviceMessage = new Message();
    static private Saver serviceSaver = new ConsoleSaver();

    public static void log(Object message) {
        serviceSaver.log(typeAnalysis(message));
    }

    public static String typeAnalysis(Object message) {
        /*
        packMessage from Message.java should be here
         */
        return serviceMessage.packMessage(message);
    }

    /**
     * finalize work with logger at all
     */
    public static void flush() {
        serviceSaver.log(serviceMessage.flush());
    }
}


