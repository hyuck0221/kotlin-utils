plugins {
    kotlin("jvm") version "2.1.10"
}

group = "com.hshim"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.locationtech.jts:jts-core:1.18.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    implementation("org.json:json:20231013")
    implementation("com.github.f4b6a3:ulid-creator:5.2.3")
    implementation("commons-codec:commons-codec:1.17.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}