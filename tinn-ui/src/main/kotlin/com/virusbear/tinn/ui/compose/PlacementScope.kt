package com.virusbear.tinn.ui.compose

interface PlacementScope {
    val x: Int
    val y: Int

    fun Placeable.place(x: Int, y: Int) {
        placeAt(this@PlacementScope.x + x, this@PlacementScope.y + y)
    }
}