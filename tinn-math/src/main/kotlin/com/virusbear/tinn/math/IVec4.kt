package com.virusbear.tinn.math

import kotlin.math.pow
import kotlin.math.sqrt

data class IVec4(
    val x: Int,
    val y: Int,
    val z: Int,
    val w: Int
) {
    val length by lazy {
        sqrt(x.toDouble().pow(2) + y.toDouble().pow(2) + z.toDouble().pow(2) + w.toDouble().pow(2))
    }
}