package com.virusbear.tinn.ui

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
    fun separator()
    fun checkbox(label: String, checked: Boolean): Boolean
    //TODO: implement more ui context elements
}