package com.melodebug;

import java.lang.instrument.Instrumentation;

public class MeloAgent {
    private static final String AGENT_NAME = "MELO_AGENT";

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println(AGENT_NAME);
    }
}
