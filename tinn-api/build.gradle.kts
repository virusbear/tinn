plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-color"))
    implementation(project(":tinn-math"))

    api("io.github.classgraph:classgraph:4.8.152")
    api(kotlin("reflect"))
}