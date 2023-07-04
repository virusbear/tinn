package com.virusbear.tinn.math

import kotlin.math.pow
import kotlin.math.sqrt

data class Vec4(
    val x: Double,
    val y: Double,
    val z: Double,
    val w: Double
) {
    companion object {
        val ZERO = Vec4(0.0, 0.0, 0.0, 0.0)
    }
    val length by lazy {
        sqrt(x.pow(2) + y.pow(2) + z.pow(2) + w.pow(2))
    }
}