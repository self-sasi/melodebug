# Build commands from the root directory

## Build command for all modules
- `./gradlew clean build`

## Run sample app without agents
- `./gradlew :melo-sample-app:build` 
- `java -cp melo-sample-app/build/classes/java/main com.melodebug.sample.SampleApp`

## Run sample app with standard OTel agents
- `./gradlew :melo-sample-app:clean :melo-sample-app:build`
- ```bash
  java \
  -javaagent:melo-sample-app/build/libs/opentelemetry-javaagent.jar \
  -Dotel.instrumentation.httpclient.enabled=true \
  -Dotel.traces.exporter=logging \
  -Dotel.metrics.exporter=none \
  -Dotel.logs.exporter=none \
  -Dotel.service.name=melo-sample-app \
  -cp melo-sample-app/build/classes/java/main \
    com.melodebug.sample.SampleApp```

## Run sample app with custom agents
- `./gradlew :melo-agent:jar`
- `java -javaagent:melo-agent/build/libs/melo-agent.jar -cp melo-sample-app/build/classes/java/main com.melodebug.sample.SampleApp`

