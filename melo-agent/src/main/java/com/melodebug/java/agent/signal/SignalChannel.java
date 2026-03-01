package com.melodebug.java.agent.signal;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class SignalChannel {
    private static final Logger LOGGER = Logger.getLogger("SignalChannel");
    private static final Set<Object> SUBSCRIBERS; // TODO: add subscriber class/interface

    static {
        SUBSCRIBERS = new HashSet<>();
    }

    public static void subscribe(Object subscriber) {
        if (subscriber == null) {
            return;
        }
        SUBSCRIBERS.add(subscriber);
    }

    public static boolean isPresent(Object subscriber) {
        return SUBSCRIBERS.contains(subscriber);
    }
}