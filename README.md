# Kotlin Utils

A convenient common utility library optimized for Kotlin.

[한국어](README_KR.md)

## Installation

### Gradle (Kotlin DSL)
```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.hyuck0221:kotlin-utils:0.0.4")
}
```

### Gradle (Groovy)
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.hyuck0221:kotlin-utils:0.0.4'
}
```

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.hyuck0221</groupId>
    <artifactId>kotlin-utils</artifactId>
    <version>0.0.4</version>
</dependency>
```

## Features

- **ClassUtil** - Class reflection utilities
- **CommonUtil** - General purpose utilities
- **DateUtil** - Date manipulation
- **EncryptUtil** - Encryption helpers
- **FormatCheckUtil** - Format validation
- **MaskingUtil** - Data masking
- **MathUtil** - Math operations
- **NumberUtil** - Number utilities
- **StringUtil** - String manipulation
- **TimeUtil** - Time operations

## Requirements

- JVM 8+
- Kotlin 1.8+

## License

This project is available under the MIT License.
