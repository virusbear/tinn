package com.virusbear.tinn.ui.compose

interface Placeable {
    val width: Int
    val height: Int

    fun placeAt(x: Int, y: Int)
}