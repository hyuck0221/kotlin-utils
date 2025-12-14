# Kotlin Utils

Kotlin에 최적화된 편리한 공통 유틸리티 라이브러리입니다.

[English](README.md)

## 설치

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

## 기능

- **ClassUtil** - 클래스 리플렉션 유틸리티
- **CommonUtil** - 범용 유틸리티
- **DateUtil** - 날짜 처리
- **EncryptUtil** - 암호화 헬퍼
- **FormatCheckUtil** - 형식 검증
- **MaskingUtil** - 데이터 마스킹
- **MathUtil** - 수학 연산
- **NumberUtil** - 숫자 유틸리티
- **StringUtil** - 문자열 처리
- **TimeUtil** - 시간 연산

## 요구사항

- JVM 8+
- Kotlin 1.8+

## 라이선스

MIT 라이선스로 제공됩니다.
