package com.melodebug.agent;

import io.opentelemetry.javaagent.OpenTelemetryAgent;

import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MeloAgent {
    private static final String AGENT_NAME = "MELO_AGENT";
    private static final Logger LOGGER = Logger.getLogger(AGENT_NAME);

    public static void premain(String agentArgs, Instrumentation inst) {
        LOGGER.log(Level.INFO, "Starting Agent.");
        OpenTelemetryAgent.premain(agentArgs, inst);
    }
}
