package com.virusbear.tinn.ui

import com.virusbear.tinn.ColorBuffer

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
    fun int(label: String, setter: (Int) -> Unit, getter: () -> Int)
    fun double(label: String, setter: (Double) -> Unit, getter: () -> Double)
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