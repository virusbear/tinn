package com.virusbear.tinn.ui.widgets

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.extensions.isolated
import com.virusbear.tinn.math.Vec2

class DrawableWidget(
    private val drawable: Drawable
): Widget() {
    override var size: Vec2 = drawable.size

    override fun measure(constraints: Constraints): Vec2 =
        size

    override fun draw(drawer: Drawer, bounds: Vec2) {
        drawer.isolated {
            draw(drawable)
        }
    }
}

fun Drawable(drawable: Drawable): DrawableWidget =
    DrawableWidget(drawable)