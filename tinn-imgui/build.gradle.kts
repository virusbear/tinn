plugins {
    kotlin("jvm")
}

val imguiVersion = "1.86.4"

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-core"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))
    implementation(project(":tinn-opengl"))
    api(project(":tinn-ui"))

    implementation("io.github.spair:imgui-java-binding:$imguiVersion")
    implementation("io.github.spair:imgui-java-lwjgl3:$imguiVersion")
    implementation("io.github.spair:imgui-java-natives-windows:$imguiVersion")
}