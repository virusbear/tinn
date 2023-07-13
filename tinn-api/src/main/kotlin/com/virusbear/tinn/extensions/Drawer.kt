package com.virusbear.tinn.extensions

import com.virusbear.tinn.draw.Drawer

fun Drawer.isolated(block: Drawer.() -> Unit) {
    push()
    block()
    pop()
}