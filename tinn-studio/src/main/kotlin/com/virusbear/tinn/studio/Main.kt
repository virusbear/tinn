package com.virusbear.tinn.studio

import com.virusbear.tinn.Driver
import com.virusbear.tinn.Program
import com.virusbear.tinn.Window
import com.virusbear.tinn.imgui.ImGuiPanel
import com.virusbear.tinn.imgui.ImGuiUIContext
import com.virusbear.tinn.nodes.NodeManager
import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.opengl.DriverGL
import com.virusbear.tinn.studio.panels.*
import imgui.ImGui

fun main() {
    Driver.use(DriverGL())

    val window: Window = Driver.use {
        init()
        createWindow(800, 600, "Hello tinn!", resizable = true, vsync = true)
    }

    val context = ImGuiUIContext("#version 130", window)
    context.init()

    NodeManager.load()
    println(Program.current)

    val panels = listOf(
        Controls(),
        NodeEditor(),
        ViewPort(),
        NodeList()
    )

    val dockSpace = DockSpace()
    dockSpace.init(context)
    panels.forEach { it.init(context) }

    loop(window) {
        context.render { ctx ->
            if(ImGui.begin(dockSpace.name, (dockSpace as ImGuiPanel).windowFlags()))
                dockSpace.render(ctx)
            ImGui.end()

            panels.forEach {
                if(ImGui.begin(it.name))
                    it.render(ctx)
                ImGui.end()
            }

            Program.current.update()

            ImGui.begin("Properties")
            ImGui.end()
        }
    }

    panels.forEach {
        it.destroy()
    }
    dockSpace.destroy()

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