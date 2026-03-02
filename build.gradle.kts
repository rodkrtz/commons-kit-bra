plugins {
    kotlin("jvm") version "2.3.0"
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

group = "com.rodkrtz"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("com.rodkrtz:commons-kit:0.0.1")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
    explicitApi()
}

tasks.test {
    useJUnitPlatform()
}