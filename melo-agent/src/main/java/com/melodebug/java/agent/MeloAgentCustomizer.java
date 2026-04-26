package com.melodebug.java.agent;

import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizer;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider;

public class MeloAgentCustomizer implements AutoConfigurationCustomizerProvider {

    static {
        // This is the "loudest" log possible
        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.err.println("!!! MELO DEBUG: Customizer Class LOADED !!!");
        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    @Override
    public void customize(AutoConfigurationCustomizer autoConfiguration) {
        System.out.println(">>> MELO DEBUG: Customizer class instantiated!");
        autoConfiguration.addTracerProviderCustomizer(
            (sdkTracerProviderBuilder, configProperties) -> 
                sdkTracerProviderBuilder.addSpanProcessor(new MeloSpanProcessor())
        );
    }
}
