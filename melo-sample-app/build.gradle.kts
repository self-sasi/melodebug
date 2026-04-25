plugins {
    id("java")
}

dependencies {
    implementation(project(":melo-agent-api"))
    implementation("io.opentelemetry.javaagent:opentelemetry-javaagent:2.25.0")
}

tasks.register<Copy>("copyAgentJar") {
    from(configurations.runtimeClasspath) 
    include("opentelemetry-javaagent-*.jar")
    into("$buildDir/libs")  
    rename { "opentelemetry-javaagent.jar" } 
}

tasks.build { dependsOn("copyAgentJar") }