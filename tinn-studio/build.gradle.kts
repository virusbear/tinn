plugins {
    kotlin("jvm")
    application
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
}