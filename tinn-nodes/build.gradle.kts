plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-api"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))

    api("io.github.classgraph:classgraph:4.8.152")
    api(kotlin("reflect"))
    api("org.jgrapht:jgrapht-core:1.5.1")

    api("dev.dewy:nbt:1.5.1")
}