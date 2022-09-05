package com.virusbear.tinn.math

import kotlin.math.pow
import kotlin.math.sqrt

data class Vec2(
    val x: Double,
    val y: Double
) {
    val length by lazy {
        sqrt(x.pow(2.0) + y.pow(2.0))
    }
}