package com.virusbear.tinn.math

import kotlin.math.atan2
import kotlin.math.sqrt

data class IVec2(
    val x: Int,
    val y: Int
) {
    companion object {
        val ZERO = IVec2(0, 0)
        val ONE = IVec2(1, 1)
        val UNIT_X = IVec2(1, 0)
        val UNIT_Y = IVec2(0, 1)
    }

    val squaredLength: Int =
        x * x + y * y

    val length: Double =
        sqrt(squaredLength.toDouble())

    val heading: Double =
        atan2(y.toDouble(), x.toDouble())

    operator fun plus(other: IVec2): IVec2 =
        IVec2(x + other.x, y + other.y)
    operator fun plus(scalar: Number): IVec2 =
        IVec2(x + scalar.toInt(), y + scalar.toInt())

    operator fun minus(other: IVec2): IVec2 =
        IVec2(x - other.x, y - other.y)
    operator fun minus(scalar: Number): IVec2 =
        IVec2(x - scalar.toInt(), y - scalar.toInt())

    operator fun unaryMinus(): IVec2 =
        IVec2(-x, -y)

    operator fun times(scalar: Number): IVec2 =
        IVec2(x * scalar.toInt(), y * scalar.toInt())

    operator fun div(scalar: Number): IVec2 =
        IVec2(x / scalar.toInt(), y / scalar.toInt())

    infix fun cross(v: IVec2): Int =
        x * v.y - y * v.x

    infix fun dot(v: IVec2): Int =
        x * v.x + y * v.y

    fun squaredDistanceTo(other: IVec2): Int {
        val dx = other.x - x
        val dy = other.y - y

        return dx * dx + dy * dy
    }

    fun distanceTo(other: IVec2): Double =
        sqrt(squaredDistanceTo(other).toDouble())
}