# MeloDebug Architecture

## Overview

MeloDebug is a Java agent that instruments application performance using OpenTelemetry (OTEL) and sonifies it in real time. The agent "hitches a ride" on the OTEL Java agent via an extension mechanism, registering a custom span processor to capture performance events and trigger audio signals.

The project uses a multi-module Gradle build with separate artifacts for the agent wrapper, extension, API, and sample app to avoid classloader conflicts.

## Architecture Diagram

```
JVM Startup
├── -javaagent:opentelemetry-javaagent.jar (official OTEL agent)
└── -Dotel.javaagent.extensions=melo-agent-extension.jar (custom extension)

OpenTelemetry Agent
├── Loads built-in instrumentations (HTTP, JDBC, etc.)
├── Discovers extensions via SPI
│   └── META-INF/services/io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider
└── Calls MeloAgentCustomizer.customize(...) during initialization

MeloAgentCustomizer
└── Registers MeloSpanProcessor with TracerProvider
    └── Adds span processor to OTEL pipeline

Span Processing Flow
├── HTTP Request → OTEL Instrumentation → Span Created
├── Span Start → MeloSpanProcessor.onStart() → SignalChannel.publish("request")
├── Span End → MeloSpanProcessor.onEnd() → SignalChannel.publish("response")
└── Subscribers receive signals → Play interface → Audio output

Module Structure
├── melo-agent-api/
│   ├── SignalChannel (pub-sub registry)
│   ├── Signal (event types)
│   └── Subscriber + Play (interfaces)
├── melo-agent/
│   ├── MeloAgent (wrapper bootstrap)
│   ├── MeloAgentCustomizer (SPI extension)
│   └── MeloSpanProcessor (span event handler)
└── melo-sample-app/
    └── SampleApp (test HTTP client)
```

## Components

### MeloAgent (Wrapper)
- Bootstrap agent with `premain(...)` method
- Delegates to official OTEL agent
- Manifest: `Premain-Class: com.melodebug.java.agent.MeloAgent`

### MeloAgentCustomizer (Extension)
- Implements `AutoConfigurationCustomizerProvider`
- Registered via SPI in `META-INF/services/...`
- Adds `MeloSpanProcessor` to OTEL's tracer provider

### MeloSpanProcessor
- Implements `SpanProcessor` interface
- `onStart()`: Publishes "request" signal, adds custom attributes
- `onEnd()`: Publishes "response" signal, logs span completion
- Thread-safe, no-op for shutdown/flush

### SignalChannel
- Static pub-sub registry using `ConcurrentHashMap`
- `subscribe(Subscriber)`: Registers listeners
- `publish(Signal)`: Notifies all subscribers asynchronously

### Signal, Subscriber, Play
- `Signal`: Enum for event types (REQUEST_START, RESPONSE_END, etc.)
- `Subscriber`: Interface for event listeners
- `Play`: Interface for audio output (playRequest(), playResponse())

### Sample App
- HTTP client making requests to `httpbin.org`
- Instrumented by OTEL agent
- Generates spans for testing sonification

## Data Flow

1. **JVM Start**: OTEL agent loads with extension jar
2. **Agent Init**: SPI discovers `MeloAgentCustomizer`, registers `MeloSpanProcessor`
3. **App Run**: Sample app makes HTTP request
4. **Instrumentation**: OTEL creates span for HTTP call
5. **Span Events**: Processor hooks into start/end, publishes signals
6. **Sonification**: Subscribers receive signals, trigger audio via Play

## Build and Deployment

### Gradle Multi-Module
- `settings.gradle.kts`: Includes all modules
- `melo-agent/build.gradle.kts`: Shadow plugin for fat jar, compileOnly OTEL deps
- Separate wrapper/extension jars to avoid classloader issues

### Dependencies
- `opentelemetry-javaagent:2.25.0`: Official agent
- `opentelemetry-sdk-extension-autoconfigure:1.37.0` (compileOnly): SPI interfaces
- `opentelemetry-api:1.37.0`: Span APIs

### Runtime Command
```bash
java \
  -javaagent:path/to/opentelemetry-javaagent.jar \
  -Dotel.javaagent.extensions=melo-agent/build/libs/melo-agent-extension.jar \
  -Dotel.traces.exporter=logging \
  -Dotel.javaagent.debug=true \
  -cp melo-sample-app/build/classes/java/main \
  com.melodebug.sample.SampleApp
```

## Lessons Learned

### What Went Wrong
- **Mixed Jar Roles**: One jar acting as agent + extension caused SPI/classloader conflicts
- **Duplicate Classes**: `implementation` bundled OTEL SPI classes, incompatible with agent's copies
- **Conditional Logging**: `onStart` guard prevented logging, hiding processor activity

### Fixes Applied
- **Jar Split**: Separate wrapper and extension artifacts
- **compileOnly Deps**: Avoid bundling OTEL SPI classes in extension jar
- **SPI Registration**: Clean extension loading via service provider interface

### Why It Works
- **Classloader Isolation**: Extension jar uses agent's SPI classes at runtime
- **SPI Discovery**: OTEL finds customizer via `META-INF/services` file
- **Processor Integration**: Custom span processor hooks into OTEL pipeline seamlessly
- **Thread-Safe Pub-Sub**: SignalChannel enables decoupled sonification without blocking spans

## Next Steps

1. Implement `Play` interface with actual audio library (e.g., Java Sound API)
2. Add more span types (database, method calls) to sonification
3. Performance tuning for low-latency audio triggers
4. Testing with real applications beyond sample app</content>
<parameter name="filePath">c:\Users\maikh\Personal Project\Project - Java Agent Sonified\melodebug\docs\architecture.md
