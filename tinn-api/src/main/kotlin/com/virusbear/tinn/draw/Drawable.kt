package com.virusbear.tinn.draw

import com.virusbear.tinn.Driver
import com.virusbear.tinn.math.Vec2

interface Drawable {
    val size: Vec2
}

fun drawable(size: Vec2, drawable: Drawer.() -> Unit): Drawable =
    Driver.driver.createDrawable(size, drawable)