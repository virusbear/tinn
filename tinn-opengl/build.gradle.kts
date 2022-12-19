plugins {
    kotlin("jvm")
}

val libraries = listOf("", "-glfw", "-nanovg", "-nfd", "-opengl")
val lwjglVersion = "3.3.1"

val lwjglNatives = Pair(
    System.getProperty("os.name")!!,
    System.getProperty("os.arch")!!
).let { (name, arch) ->
    when {
        arrayOf("Linux", "FreeBSD", "SunOS", "Unit").any { name.startsWith(it) } ->
            if (arrayOf("arm", "aarch64").any { arch.startsWith(it) })
                "natives-linux${if (arch.contains("64") || arch.startsWith("armv8")) "-arm64" else "-arm32"}"
            else
                "natives-linux"
        arrayOf("Windows").any { name.startsWith(it) }                           ->
            "natives-windows"
        else -> throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":tinn-api"))
    implementation(project(":tinn-math"))
    implementation(project(":tinn-color"))

    api(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
    libraries.forEach {
        api("org.lwjgl", "lwjgl$it")
        runtimeOnly("org.lwjgl", "lwjgl$it", classifier = lwjglNatives)
    }
}