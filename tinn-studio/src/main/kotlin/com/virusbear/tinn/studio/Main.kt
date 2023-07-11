package com.virusbear.tinn.studio

import com.virusbear.tinn.Driver
import com.virusbear.tinn.MultiSample
import com.virusbear.tinn.Window
import com.virusbear.tinn.opengl.DriverGL
import com.virusbear.tinn.renderTarget

fun main() {
    Driver.use(DriverGL())

    val window: Window = Driver.use {
        init()
        createWindow(800, 600, "tinn", resizable = true, vsync = true, multisample = MultiSample.None)
    }

    val renderTarget = window.renderTarget
    println(renderTarget)

    loop(window) {

    }

    Driver.driver.destroy()
}

private fun loop(window: Window, block: () -> Unit) {
    window.bound {
        while (window.open) {
            window.clear()

            block()

            window.update()
        }
    }
}