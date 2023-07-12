package com.virusbear.tinn.imgui

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.MultiSample
import com.virusbear.tinn.TextureFilter
import com.virusbear.tinn.Window
import com.virusbear.tinn.math.*
import com.virusbear.tinn.opengl.ColorBufferGL
import com.virusbear.tinn.ui.NodeEditorUIContext
import com.virusbear.tinn.ui.NodeUIContext
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui.createContext
import imgui.ImGui.destroyContext
import imgui.extension.imnodes.ImNodes
import imgui.extension.imnodes.flag.ImNodesMiniMapLocation
import imgui.flag.ImGuiConfigFlags
import imgui.flag.ImGuiMouseButton
import imgui.flag.ImGuiTreeNodeFlags
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import imgui.internal.ImGui
import imgui.internal.ImGuiContext
import imgui.type.ImDouble
import imgui.type.ImInt
import imgui.type.ImString
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11C.GL_UNPACK_ROW_LENGTH
import org.lwjgl.opengl.GL11C.glPixelStorei
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

    override fun button(label: String, text: String, onClick: () -> Unit) {
        ImGui.pushID(label)
        if(ImGui.button(label)) onClick()
        ImGui.popID()
    }

    override fun imageButton(label: String, colorBuffer: ColorBuffer, onClick: () -> Unit) {
        if(colorBuffer is ColorBufferGL && colorBuffer.multisample != MultiSample.None) {
            error("ImGUI does not support multisample image rendering.")
        }

        ImGui.pushID(label)
        if(ImGui.imageButton(colorBuffer.textureId, colorBuffer.width.toFloat(), colorBuffer.height.toFloat())) {
            onClick()
        }
        ImGui.popID()
    }

    override fun comboBox(label: String, options: List<String>, idx: Int, onSelect: (Int) -> Unit) {
        val index = ImInt(idx)
        ImGui.combo(label, index, options.toTypedArray())
        if(index.get() != idx) {
            onSelect(index.get())
        }
    }

    override fun text(text: String) {
        ImGui.text(text)
    }

    override fun textInput(label: String, setter: (String) -> Unit, getter: () -> String) {
        val value = ImString(getter(), 250)
        if(ImGui.inputText(label, value)) {
            setter(value.get())
        }
    }

    override fun int(label: String, setter: (Int) -> Unit, getter: () -> Int) {
        val value = ImInt(getter())
        if(ImGui.inputInt(label, value)) {
            setter(value.get())
        }
    }

    override fun double(label: String, setter: (Double) -> Unit, getter: () -> Double) {
        val value = ImDouble(getter())
        if(ImGui.inputDouble(label, value)) {
            setter(value.get())
        }
    }

    override fun iVec2(label: String, setter: (IVec2) -> Unit, getter: () -> IVec2) {
        val value = IntArray(2)
        value[0] = getter().x
        value[1] = getter().y

        if(ImGui.inputInt2(label, value)) {
            setter(IVec2(value[0], value[1]))
        }
    }

    override fun iVec3(label: String, setter: (IVec3) -> Unit, getter: () -> IVec3) {
        val value = IntArray(3)
        value[0] = getter().x
        value[1] = getter().y
        value[2] = getter().z

        if(ImGui.inputInt3(label, value)) {
            setter(IVec3(value[0], value[1], value[2]))
        }
    }

    override fun iVec4(label: String, setter: (IVec4) -> Unit, getter: () -> IVec4) {
        val value = IntArray(4)
        value[0] = getter().x
        value[1] = getter().y
        value[2] = getter().z
        value[3] = getter().w

        if(ImGui.inputInt4(label, value)) {
            setter(IVec4(value[0], value[1], value[2], value[3]))
        }
    }

    override fun vec2(label: String, setter: (Vec2) -> Unit, getter: () -> Vec2) {
        val value = FloatArray(2)
        value[0] = getter().x.toFloat()
        value[1] = getter().y.toFloat()

        if(ImGui.inputFloat2(label, value)) {
            setter(Vec2(value[0].toDouble(), value[1].toDouble()))
        }
    }

    override fun vec3(label: String, setter: (Vec3) -> Unit, getter: () -> Vec3) {
        val value = FloatArray(3)
        value[0] = getter().x.toFloat()
        value[1] = getter().y.toFloat()
        value[2] = getter().z.toFloat()

        if(ImGui.inputFloat3(label, value)) {
            setter(Vec3(value[0].toDouble(), value[1].toDouble(), value[2].toDouble()))
        }
    }

    override fun vec4(label: String, setter: (Vec4) -> Unit, getter: () -> Vec4) {
        val value = FloatArray(4)
        value[0] = getter().x.toFloat()
        value[1] = getter().y.toFloat()
        value[2] = getter().z.toFloat()
        value[3] = getter().w.toFloat()

        if(ImGui.inputFloat4(label, value)) {
            setter(Vec4(value[0].toDouble(), value[1].toDouble(), value[2].toDouble(), value[3].toDouble()))
        }
    }

    override fun separator() {
        ImGui.separator()
    }

    override fun checkbox(label: String, checked: Boolean, onCheck: (Boolean) -> Unit) {
        onCheck(ImGui.checkbox(label, checked))
    }

    override fun image(label: String, colorBuffer: ColorBuffer) {
        if(colorBuffer is ColorBufferGL && colorBuffer.multisample != MultiSample.None) {
            error("ImGUI does not support multisample image rendering.")
        }

        colorBuffer.bound {
            colorBuffer.filter(TextureFilter.LINEAR, TextureFilter.LINEAR)
            glPixelStorei(GL_UNPACK_ROW_LENGTH, 0)
        }

        ImGui.pushID(label)
        ImGui.image(colorBuffer.textureId, colorBuffer.width.toFloat(), colorBuffer.height.toFloat())
        ImGui.popID()
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