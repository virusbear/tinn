package com.virusbear.tinn.ui.compose

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer

abstract class Node {
    val children = mutableListOf<Node>()

    abstract fun draw(drawer: Drawer)
}

class LayoutNode: Node() {
    override fun draw(drawer: Drawer) {
        TODO("Not yet implemented")
    }
}

class DrawableNode: Node() {
    var width: Int = 0
    var height: Int = 0
    var drawable: Drawer.() -> Unit = {}

    override fun draw(drawer: Drawer) {
        TODO("Not yet implemented")
    }
}