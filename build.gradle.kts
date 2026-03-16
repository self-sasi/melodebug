plugins {
    id("java")
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

subprojects {
    repositories {
        mavenCentral()
    }

    plugins.withId("java-library") {
        dependencies {
            testImplementation(platform("org.junit:junit-bom:5.10.0"))
            testImplementation("org.junit.jupiter:junit-jupiter")
            testRuntimeOnly("org.junit.platform:junit-platform-launcher")

            testImplementation("org.mockito:mockito-core:5.+")
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}
