package com.virusbear.tinn.imgui

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.MultiSample
import com.virusbear.tinn.Window
import com.virusbear.tinn.opengl.ColorBufferGL
import com.virusbear.tinn.ui.NodeEditorUIContext
import com.virusbear.tinn.ui.NodeUIContext
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui.*
import imgui.extension.imnodes.ImNodes
import imgui.extension.imnodes.flag.ImNodesMiniMapLocation
import imgui.flag.ImGuiConfigFlags
import imgui.flag.ImGuiMouseButton
import imgui.flag.ImGuiTreeNodeFlags
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import imgui.internal.ImGui
import imgui.internal.ImGuiContext
import imgui.type.ImInt
import org.lwjgl.glfw.GLFW
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
        if(colorBuffer is ColorBufferGL && colorBuffer.samples != MultiSample.None) {
            error("ImGUI does not support multisample image rendering.")
        }

        ImGui.image(colorBuffer.textureId, colorBuffer.width.toFloat(), colorBuffer.height.toFloat())
    }

    override fun treeView(label: String, children: () -> Unit) {
        if(ImGui.treeNode(label)) {
            children()
            ImGui.treePop()
        }
    }

    override fun treeLeaf(label: String, onSelect: () -> Unit) {
        if(ImGui.treeNodeEx(label, ImGuiTreeNodeFlags.Leaf)) {
            ImGui.treePop()

            if(ImGui.isItemClicked(ImGuiMouseButton.Left) && ImGui.isMouseDoubleClicked(ImGuiMouseButton.Left)) {
                onSelect()
            }
        }
    }

    override fun nodeEditor(showMinimap: Boolean, onLinkCreated: (startAttribute: Int, endAttribute: Int) -> Unit, onLinkDestroyed: (link: Int) -> Unit, block: NodeEditorUIContext.() -> Unit) {
        ImNodes.beginNodeEditor()

        ImGuiNodeEditorUIContext().apply(block)

        if(showMinimap) {
            ImNodes.miniMap(0.2f, ImNodesMiniMapLocation.TopRight)
        }
        ImNodes.endNodeEditor()

        val startAttrId = ImInt()
        val endAttrId = ImInt()

        if(ImNodes.isLinkCreated(startAttrId, endAttrId)) {
            onLinkCreated(startAttrId.get(), endAttrId.get())
        }

        val linkId = ImInt()
        if(ImNodes.isLinkDestroyed(linkId)) {
            onLinkDestroyed(linkId.get())
        }
    }

    override fun sameLine() {
        ImGui.sameLine()
    }
}

class ImGuiNodeEditorUIContext: NodeEditorUIContext {
    override fun node(id: Int, label: String, block: NodeUIContext.() -> Unit) {
        ImNodes.beginNode(id)

        ImNodes.beginNodeTitleBar()
        ImGui.textUnformatted(label)
        ImNodes.endNodeTitleBar()

        ImGuiNodeUIContext().apply(block)

        ImNodes.endNode()
    }

    override fun link(id: Int, startAttribute: Int, endAttribute: Int) {
        ImNodes.link(id, startAttribute, endAttribute)
    }
}

class ImGuiNodeUIContext: NodeUIContext {
    override fun input(id: Int, label: String) {
        ImNodes.beginInputAttribute(id)
        ImGui.textUnformatted(label)
        ImNodes.endInputAttribute()
    }

    override fun output(id: Int, label: String) {
        ImNodes.beginOutputAttribute(id)
        ImGui.textUnformatted(label)
        ImNodes.endOutputAttribute()
    }
}