plugins {
    kotlin("jvm")
    application
    id("org.jetbrains.compose") version "1.4.1"
}

val imguiVersion = "1.86.6"

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-api"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))
    implementation(project(":tinn-opengl"))
    implementation(project(":tinn-ui"))
    implementation(project(":tinn-nodes"))
    implementation(project(":tinn-core"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
}