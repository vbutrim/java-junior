package com.db;

@FunctionalInterface
public interface EventHandler {
    void handleEvent(Object message);
}