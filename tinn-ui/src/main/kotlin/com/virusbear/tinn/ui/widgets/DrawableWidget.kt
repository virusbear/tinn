package com.virusbear.tinn.ui.widgets

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.extensions.isolated
import com.virusbear.tinn.math.Vec2

class DrawableWidget(
    private val drawable: Drawable,
    private val scaling: Scaling = Scaling.None
): Widget() {
    private var scale = Vec2.ONE

    override var size: Vec2
        get() = Vec2(drawable.size.x * scale.x, drawable.size.y * scale.y)
        set(value) {
            when(scaling) {
                Scaling.Uniform -> scale = Vec2.ONE * minOf(value.x / drawable.size.x, value.y / drawable.size.y)
                Scaling.NonUniform -> scale = Vec2(value.x / drawable.size.x, value.y / drawable.size.y)
                else -> {}
            }
        }

    override fun draw(drawer: Drawer) {
        drawer.isolated {
            scale(scale)
            draw(drawable)
        }
    }

    enum class Scaling {
        Uniform,
        NonUniform,
        None
    }
}