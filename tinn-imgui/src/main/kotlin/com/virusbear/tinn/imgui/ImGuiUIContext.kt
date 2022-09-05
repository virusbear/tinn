package com.virusbear.tinn.imgui

import com.virusbear.tinn.ui.UIContext
import imgui.ImGui.*
import imgui.flag.ImGuiConfigFlags
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import imgui.internal.ImGui
import imgui.internal.ImGuiContext
import org.lwjgl.glfw.GLFW
import java.util.*

class ImGuiUIContext(private val glslVersion: String, private val window: Long): UIContext {
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
        gl3.init(glslVersion)
        glfw.init(window, true)
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
            val backupWindowPtr = GLFW.glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GLFW.glfwMakeContextCurrent(backupWindowPtr);
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
}