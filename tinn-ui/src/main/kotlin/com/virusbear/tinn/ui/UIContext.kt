package com.virusbear.tinn.ui

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.math.*

interface UIContext {
    fun push()
    fun pop()

    fun init()
    fun dispose()

    fun render(block: (UIContext) -> Unit) {
        beginFrame()
        block(this)
        endFrame()
    }
    fun beginFrame()
    fun endFrame()

    fun button(label: String): Boolean
    fun text(text: String)
    fun textInput(label: String, setter: (String) -> Unit, getter: () -> String)
    fun int(label: String, setter: (Int) -> Unit, getter: () -> Int)
    fun double(label: String, setter: (Double) -> Unit, getter: () -> Double)
    fun iVec2(label: String, setter: (IVec2) -> Unit, getter: () -> IVec2)
    fun iVec3(label: String, setter: (IVec3) -> Unit, getter: () -> IVec3)
    fun iVec4(label: String, setter: (IVec4) -> Unit, getter: () -> IVec4)
    fun vec2(label: String, setter: (Vec2) -> Unit, getter: () -> Vec2)
    fun vec3(label: String, setter: (Vec3) -> Unit, getter: () -> Vec3)
    fun vec4(label: String, setter: (Vec4) -> Unit, getter: () -> Vec4)
    fun separator()
    fun checkbox(label: String, checked: Boolean): Boolean

    fun image(colorBuffer: ColorBuffer)

    fun treeView(label: String, children: () -> Unit)
    fun treeLeaf(label: String, onSelect: () -> Unit)

    fun nodeEditor(showMinimap: Boolean = true, onLinkCreated: (startAttribute: Int, endAttribute: Int) -> Unit = { _, _ -> }, onLinkDestroyed: (link: Int) -> Unit = { _ -> }, block: NodeEditorUIContext.() -> Unit)

    fun sameLine()
    //TODO: implement more ui context elements
}

interface NodeEditorUIContext {
    fun node(id: Int, label: String, block: NodeUIContext.() -> Unit)
    fun link(id: Int, startAttribute: Int, endAttribute: Int)
}

interface NodeUIContext {
    fun input(id: Int, label: String)
    fun output(id: Int, label: String)
}