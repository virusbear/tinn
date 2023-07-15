package com.virusbear.tinn.opengl

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.math.Vec2

data class DrawableGL(
    override val size: Vec2,
    val drawable: Drawer.() -> Unit
): Drawable