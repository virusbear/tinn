plugins {
    kotlin("jvm")
}

val imguiVersion = "1.86.4"

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-core"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))
}