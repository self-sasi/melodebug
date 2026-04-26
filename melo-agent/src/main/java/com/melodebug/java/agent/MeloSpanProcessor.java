package com.melodebug.java.agent;

import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.ReadWriteSpan;
import io.opentelemetry.sdk.trace.ReadableSpan;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.context.Context;

// import java.util.concurrent.CompletionException;

import io.opentelemetry.api.common.AttributeKey;
// import io.opentelemetry.javaagent.shaded.io.opentelemetry.api.common.AttributeKey;

public class MeloSpanProcessor implements SpanProcessor{
    @Override
    public void onStart(Context parentContext, ReadWriteSpan span)
    {
    System.out.println("[MeloAgent] Span started: " + span.getName());
    span.setAttribute("custom.processor", "active");
    }

    @Override
    public void onEnd(ReadableSpan span){
        System.out.println("[MeloAgent] Span ended: " + span.getName());

    }
    // Required no-op methods
    @Override
    public boolean isStartRequired() { return true; }

    @Override
    public boolean isEndRequired() { return true; }

    @Override
    public CompletableResultCode shutdown() {return forceFlush();}

    @Override
    public CompletableResultCode forceFlush() {return CompletableResultCode.ofSuccess();}
}
