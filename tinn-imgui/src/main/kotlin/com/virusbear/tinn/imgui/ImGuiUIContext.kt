package com.virusbear.tinn.imgui

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.Window
import com.virusbear.tinn.opengl.ColorBufferGL
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui.*
import imgui.ImVec2
import imgui.flag.ImGuiConfigFlags
import imgui.flag.ImGuiDockNodeFlags
import imgui.flag.ImGuiStyleVar
import imgui.flag.ImGuiWindowFlags
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import imgui.internal.ImGui
import imgui.internal.ImGuiContext
import imgui.type.ImBoolean
import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL40
import java.util.*

class ImGuiUIContext(private val glslVersion: String, private val window: Window): UIContext {
    private val contextStack = Stack<ImGuiContext>()
    private val context: ImGuiContext
        get() =
            contextStack.peek() ?: error("No ImGuiContext available")
    private val glfw = ImGuiImplGlfw()
    private val gl3 = ImGuiImplGl3()

    override fun push() {
        contextStack.push(createContext())
    }

    override fun pop() {
        try {
            contextStack.pop()
        } catch (ex: EmptyStackException) {
            error("No ImGuiContext available")
        }
    }

    override fun init() {
        push()

        val io = ImGui.getIO()
        io.configFlags = io.configFlags or ImGuiConfigFlags.DockingEnable

        gl3.init(glslVersion)
        glfw.init(window.native, true)
    }

    override fun dispose() {
        while(contextStack.size > 0) {
            destroyContext(contextStack.pop())
        }

        glfw.dispose()
        gl3.dispose()
    }

    override fun beginFrame() {
        glfw.newFrame()
        ImGui.newFrame()
    }

    override fun endFrame() {
        ImGui.endFrame()
        ImGui.render()
        gl3.renderDrawData(ImGui.getDrawData())

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            val backupWindowPtr = GLFW.glfwGetCurrentContext()
            ImGui.updatePlatformWindows()
            ImGui.renderPlatformWindowsDefault()
            GLFW.glfwMakeContextCurrent(backupWindowPtr)
        }
    }

    override fun button(label: String): Boolean =
        ImGui.button(label)

    override fun text(text: String) {
        ImGui.text(text)
    }

    override fun separator() {
        ImGui.separator()
    }

    override fun checkbox(label: String, checked: Boolean): Boolean =
        ImGui.checkbox(label, checked)

    override fun image(colorBuffer: ColorBuffer) {
        colorBuffer.bound {
            val id = IntArray(1)
            GL11.glGetIntegerv(GL11.GL_TEXTURE_BINDING_2D, id)
            println("TextureId: ${colorBuffer.textureId}")
            println("Id: ${id[0]}")
            ImGui.image(id[0], colorBuffer.width.toFloat(), colorBuffer.height.toFloat())
        }
    }
}