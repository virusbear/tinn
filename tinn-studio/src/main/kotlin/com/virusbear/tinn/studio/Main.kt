package com.virusbear.tinn.studio

import com.virusbear.tinn.*
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.opengl.DriverGL
import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.ui.*
import com.virusbear.tinn.ui.widgets.DrawableWidget
import com.virusbear.tinn.ui.widgets.FrameBufferWidget
import com.virusbear.tinn.window.Window

fun main() {
    Driver.use(DriverGL())

    val window: Window = Driver.use {
        init()
        createWindow(512, 512, "tinn", resizable = true, vsync = true, multisample = MultiSample.None)
    }

    Registries.ThemeVariables.register("tinn:icon_foreground_color", Theme.IconForegroundColor)

    Theme.IconForegroundColor(Color(0.1686274510, 0.1764705882, 0.1882352941))

    Scene(window, FrameBufferWidget(DrawableWidget(Icons.Hamburger))).run()

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