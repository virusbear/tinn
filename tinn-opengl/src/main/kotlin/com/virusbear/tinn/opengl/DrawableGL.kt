package com.virusbear.tinn.opengl

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer

@JvmInline
value class DrawableGL(val drawable: Drawer.() -> Unit): Drawable