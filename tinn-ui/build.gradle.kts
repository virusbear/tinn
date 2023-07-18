plugins {
    kotlin("jvm")
}

val imguiVersion = "1.86.4"

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-api"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))

    api("dev.dewy:nbt:1.5.1")
}