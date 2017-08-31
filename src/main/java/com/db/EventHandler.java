package com.db;

/**
 * General Interface
 */
@FunctionalInterface
public interface EventHandler {
    void handleEvent(Object message);
}