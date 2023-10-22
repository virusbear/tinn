package com.virusbear.tinn.studio

import com.virusbear.tinn.*
import com.virusbear.tinn.color.rgb
import com.virusbear.tinn.extensions.loadImage
import com.virusbear.tinn.opengl.DriverGL
import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.ui.*
import com.virusbear.tinn.ui.theme.IconForegroundColor
import com.virusbear.tinn.ui.theme.Theme
import com.virusbear.tinn.window.Window
import java.io.File

fun main() {
    Driver.use(DriverGL())

    val window: Window = Driver.use {
        init()
        createWindow(512, 512, "tinn", resizable = true, vsync = true, multisample = MultiSample.None)
    }

    TinnPlugin().onInitialize()

    Registries.ThemeVariables.register("tinn:icon_foreground_color", Theme.IconForegroundColor)
    Registries.ThemeVariables.register("tinn:icon_hover_color", Theme.IconHoverColor)
    Registries.ThemeVariables.register("tinn:icon_active_color", Theme.IconActiveColor)
    Registries.ThemeVariables.register("tinn:icon_background_color", Theme.BackgroundColor)
    Registries.ThemeVariables.register("tinn:icon_margin", Theme.IconMargin)
    Registries.ThemeVariables.register("tinn:icon_rounding", Theme.IconRounding)

    Theme.IconForegroundColor(rgb(206, 208, 214))
    Theme.IconHoverColor(rgb(57, 59, 64))
    Theme.IconActiveColor(rgb(78, 81, 87))
    Theme.BackgroundColor(rgb(43, 45, 48))
    Theme.IconMargin(0)
    Theme.IconRounding(0)

    val img = loadImage(File("H:\\git\\tinn\\prusa.png"))

    Scene(window, IconButtonWidget(Icons.Hamburger) { println("button clicked") }.also { it.active = true }).run()

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