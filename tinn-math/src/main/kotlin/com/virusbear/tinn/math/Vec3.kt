package com.virusbear.tinn.math

import kotlin.math.pow
import kotlin.math.sqrt

data class Vec3(
    val x: Double,
    val y: Double,
    val z: Double
) {
    val length by lazy {
        sqrt(x.pow(2) + y.pow(2) + z.pow(2))
    }
}