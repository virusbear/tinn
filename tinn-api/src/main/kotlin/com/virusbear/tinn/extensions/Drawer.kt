package com.virusbear.tinn.extensions

import com.virusbear.tinn.Driver
import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import java.io.File

fun Drawer.isolated(block: Drawer.() -> Unit) {
    push()
    block()
    pop()
}