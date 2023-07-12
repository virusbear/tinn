package com.virusbear.tinn.color

data class Color(
    val r: Double = 0.0,
    val g: Double = 0.0,
    val b: Double = 0.0,
    val a: Double = 1.0
) {
    companion object {
        val TRANSPARENT = Color(0.0, 0.0, 0.0, 0.0)
        val BLACK = Color(0.0, 0.0, 0.0, 1.0)
        val WHITE = Color(1.0, 1.0, 1.0, 1.0)
    }
}