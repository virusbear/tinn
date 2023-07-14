package com.virusbear.tinn.draw

import com.virusbear.tinn.Driver

interface Drawable

fun drawable(drawable: Drawer.() -> Unit): Drawable =
    Driver.driver.createDrawable(drawable)