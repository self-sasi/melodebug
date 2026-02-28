plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    // opentelemetry java agent bundled
    implementation("io.opentelemetry.javaagent:opentelemetry-javaagent:2.25.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}