package com.melodebug.java.agent.signal;

import com.melodebug.java.agent.subscriber.Subscriber;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class SignalChannel {
    private static final Logger LOGGER = Logger.getLogger("SignalChannel");
    private static final Set<Subscriber> SUBSCRIBERS;

    static {
        SUBSCRIBERS = new HashSet<>();
    }

    public static void subscribe(Subscriber subscriber) {
        if (subscriber == null) {
            return;
        }
        SUBSCRIBERS.add(subscriber);
    }

    public static void unsubscribe(Subscriber subscriber) {
        SUBSCRIBERS.remove(subscriber);
    }

    public static boolean isPresent(Object subscriber) {
        return SUBSCRIBERS.contains(subscriber);
    }
}
