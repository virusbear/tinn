package com.virusbear.tinn.math

import kotlin.math.pow
import kotlin.math.sqrt

data class IVec2(
    val x: Int,
    val y: Int
) {
    val length by lazy {
        sqrt(x.toDouble().pow(2.0) + y.toDouble().pow(2.0))
    }
}