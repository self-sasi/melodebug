## Project Structure
- melo-agent-api: 
    - Clean public contract for Signal, Subscriber and Play/sonification abstraction
- melo-agent: 
    - The agent implementation that delegates to OpenTelemetry and houses extensions and logic
- melo-sample-app:
    - Separate module to run/validate the agent

## Build Opentelemetry extension
1. Create OTEL instrumentation/extension module in the agent project
2. Register SpanProcessor in that module
3. Have processor call into melodebug-api (Play/Signal Channel)

## Wire the sample app and test runtime
1. Ensure melo-sample-app has proper package and build path
2. Run it with OTEL logging exporter to verify spans
3. Use melo-sample-app to confirm HTTP client instrumentation/other performance hooks

## Add sonification and strengthen design
1. Implement the actual sound/Play behavior in the agent layer
2. Make shared state SignalChannel thread-safe and keep APIs decoupled
3. Refine design using interfaces, clean package structure, and all integration tests




