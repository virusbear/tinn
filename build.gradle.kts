plugins {
    kotlin("jvm") version "1.7.0" apply false
}

subprojects {
    group = "com.virusbear"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        google()
    }
}