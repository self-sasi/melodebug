package com.melodebug.java.agent.play;

public interface Play {
    void onRequestStart(String url);
    void onRequestEnd(int statusCode);
}
