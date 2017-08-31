package com.acme.edu;

import com.db.*;

public class Logger {
    // static private FormattingSavingHandler serviceFormattingSavingHandler = new FormattingSavingHandler();
    // static private Saver serviceSaver = new ConsoleSaver();
    static Context myContext = new Context(
            new FormattingSavingHandler());


    public static void log(Object message) {
        // serviceSaver.log(serviceFormattingSavingHandler.packMessage(message));
        myContext.logEvent(message);
    }

    /**
     * finalize work with logger at all
     */
    public static void flush() {
        log(new FlushTrigger());
    }
        // myContext.flush();
}


