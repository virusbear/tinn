plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.4.1"
}

repositories {
    gradlePluginPortal()
}

val imguiVersion = "1.86.4"

dependencies {
    implementation(kotlin("stdlib"))

    api("org.jetbrains.compose.runtime:runtime:1.4.1")

    implementation(project(":tinn-api"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
}