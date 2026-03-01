plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    // opentelemetry java agent bundled
    implementation("io.opentelemetry.javaagent:opentelemetry-javaagent:2.25.0")

    implementation(project(":melo-agent-api"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}