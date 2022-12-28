plugins {
    kotlin("jvm")
}

val imguiVersion = "1.86.6"

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-api"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))
    implementation(project(":tinn-opengl"))
    implementation(project(":tinn-imgui"))
    implementation(project(":tinn-nodes"))
    implementation(project(":tinn-core"))

    implementation("io.github.spair:imgui-java-binding:$imguiVersion")
    implementation("io.github.spair:imgui-java-lwjgl3:$imguiVersion")
    implementation("io.github.spair:imgui-java-natives-windows:$imguiVersion")
}