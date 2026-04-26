package com.melodebug.java.agent;

import io.opentelemetry.javaagent.OpenTelemetryAgent;
import io.opentelemetry.javaagent.shaded.io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;

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