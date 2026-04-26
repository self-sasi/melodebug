
plugins {
    `java-library`
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

tasks.shadowJar {
    archiveClassifier.set("wrapper")
    mergeServiceFiles()
    exclude("com/melodebug/java/agent/MeloAgentCustomizer.class")
    exclude("META-INF/services/io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider")
    manifest {
        attributes(
            "Premain-Class" to "com.melodebug.java.agent.MeloAgent",
            "Can-Redefine-Classes" to "true",
            "Can-Retransform-Classes" to "true"
        )
    }
}

tasks.jar {
    archiveClassifier.set("extension")
}

dependencies {
    // opentelemetry java agent bundled
    implementation("io.opentelemetry.javaagent:opentelemetry-javaagent:2.25.0")
    // opentelemetry core SDK
    implementation("io.opentelemetry:opentelemetry-sdk:1.37.0") 
    // opentelemetry span APIs
    implementation("io.opentelemetry:opentelemetry-api:1.37.0")
    // opentelemetry autoconfigure extension
    compileOnly("io.opentelemetry:opentelemetry-sdk-extension-autoconfigure:1.37.0")
    implementation(project(":melo-agent-api"))
}
