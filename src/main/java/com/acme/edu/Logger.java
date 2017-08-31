package com.acme.edu;

import com.db.*;

public class Logger {
    static Context myContext = new Context(
            new FormattingSavingHandler());

    private Logger() {
    }

    public static void log(Object message) {
        myContext.logEvent(message);
    }

    /**
     * finalize work with logger at all
     */
    public static void flush() {
        log(new FlushTrigger());
    }
}


