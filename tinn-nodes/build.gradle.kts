plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-api"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))

    api("org.jgrapht:jgrapht-core:1.5.1")

    api("dev.dewy:nbt:1.5.1")
}