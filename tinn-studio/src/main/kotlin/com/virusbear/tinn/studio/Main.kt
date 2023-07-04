package com.virusbear.tinn.studio

import com.virusbear.tinn.*
import com.virusbear.tinn.events.ProgramControlEvent
import com.virusbear.tinn.imgui.ImGuiPanel
import com.virusbear.tinn.imgui.ImGuiUIContext
import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.opengl.DriverGL
import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.studio.panels.*
import imgui.ImGui
import org.lwjgl.glfw.GLFW

fun main() {
    Driver.use(DriverGL())

    val window: Window = Driver.use {
        init()
        createWindow(800, 600, "tinn", resizable = true, vsync = true)
    }

    val context = ImGuiUIContext("#version 130", window)
    context.init()

    //This is an artifact of failing to load classes correctly that create new registries.
    //Those classes need to be loaded once and their registries accessed
    //This should later be changed to a plugin based system.
    //listOf(Registries.NodeIdentifiers, Registries.PortSerializers)
    Registries.Nodes.register("tinn-studio:viewport", ViewPortNode)
    Registries.Nodes.entries().forEach {
        NodeManager.register(it)
    }
    Registries.PortSerializers.entries().forEach {
        NodeManager.register(it)
    }

    val panels = listOf(
        Controls(),
        NodeEditor(),
        ViewPort,
        NodeList(),
        Properties(),
        NodespaceStack()
    )

    val dockSpace = DockSpace()
    dockSpace.init(context)
    panels.forEach { it.init(context) }

    val program = Program()
    val menuBar = MainMenuBar(program)

    EventBus.subscribe<ProgramControlEvent> {
        when(it) {
            ProgramControlEvent.Pause -> program.stop()
            ProgramControlEvent.Reset -> program.reset()
            ProgramControlEvent.Start -> program.start()
            ProgramControlEvent.Step -> program.step()
            ProgramControlEvent.Stop -> {
                program.stop()
                program.reset()
            }
        }
    }

    loop(window) {
        context.render { ctx ->
            menuBar.render(ctx)

            if(ImGui.begin(dockSpace.name, (dockSpace as ImGuiPanel).windowFlags()))
                dockSpace.render(ctx)
            ImGui.end()

            panels.forEach {
                if(ImGui.begin(it.name))
                    it.render(ctx)
                ImGui.end()
            }

            //Back button (Logitech MX Master 3S)
            if(ImGui.isMouseClicked(GLFW.GLFW_MOUSE_BUTTON_4)) {
                EventBus.publish(NodespacePopEvent)
            }

            program.update()
        }
    }

    panels.forEach {
        it.destroy()
    }
    dockSpace.destroy()

    program.destroy()

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

object NodespacePopEvent: Event