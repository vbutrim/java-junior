package com.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * "choose handlers" class
 */
public class Context {
    private List<EventHandler> handlers = new ArrayList<>();

    public Context(EventHandler... handlers) {
        this.handlers.addAll(asList(handlers));
    }

    public void logEvent(Object message) {
        handlers.forEach((EventHandler h) -> {
            try {
                h.handleEvent(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
