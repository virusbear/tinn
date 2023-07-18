package com.virusbear.tinn.ui.widgets

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.extensions.isolated
import com.virusbear.tinn.math.Vec2

class DrawableWidget(
    private val drawable: Drawable
): Widget() {
    override var size: Vec2 = drawable.size

    override fun draw(drawer: Drawer) {
        drawer.isolated {
            draw(drawable)
        }
    }
}

fun Drawable(drawable: Drawable): DrawableWidget =
    DrawableWidget(drawable)