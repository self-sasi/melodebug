plugins {
    `java-library`
}

dependencies {
    // opentelemetry java agent bundled
    implementation("io.opentelemetry.javaagent:opentelemetry-javaagent:2.25.0")
    implementation(project(":melo-agent-api"))
}
