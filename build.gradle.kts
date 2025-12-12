plugins {
    kotlin("jvm") version "2.1.10"
}

group = "com.hshim"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-web:6.2.8")
    implementation("org.locationtech.jts:jts-core:1.18.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    implementation("org.json:json:20231013")
    implementation("com.github.f4b6a3:ulid-creator:5.2.3")
    implementation("commons-codec:commons-codec:1.17.2")
    implementation("org.apache.poi:poi:4.0.0")
    implementation("org.apache.poi:poi-ooxml:5.4.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)

    compilerOptions {
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_8)
        languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_8)
    }
}