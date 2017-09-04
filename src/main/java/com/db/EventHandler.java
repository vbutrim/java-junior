package com.db;

import java.io.IOException;

/**
 * General Interface
 */
@FunctionalInterface
public interface EventHandler {
    void handleEvent(Object message) throws IOException;
}