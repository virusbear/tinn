plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-api"))
    implementation(project(":tinn-color"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-opengl"))
    implementation(project(":tinn-nodes"))

    api("dev.dewy:nbt:1.5.1")
}