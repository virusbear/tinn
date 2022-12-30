plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-api"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))

    implementation("io.github.classgraph:classgraph:4.8.152")
    implementation(kotlin("reflect"))
    implementation("org.jgrapht:jgrapht-core:1.5.1")

    implementation("dev.dewy:nbt:1.5.1")
}