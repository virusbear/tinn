package com.virusbear.tinn.math

import kotlin.math.sqrt

data class IVec4(
    val x: Int,
    val y: Int,
    val z: Int,
    val w: Int
) {
    companion object {
        val ZERO = IVec4(0, 0, 0, 0)
        val ONE = IVec4(1, 1, 1, 1)
        val UNIT_X = IVec4(1, 0, 0, 0)
        val UNIT_Y = IVec4(0, 1, 0, 0)
        val UNIT_Z = IVec4(0, 0, 1, 0)
        val UNIT_W = IVec4(0, 0, 0, 1)
    }

    val squaredLength: Double =
        x.toDouble() * x.toDouble() + y.toDouble() * y.toDouble() + z.toDouble() * z.toDouble() + w.toDouble() * w

    val length by lazy {
        sqrt(squaredLength)
    }

    operator fun plus(other: IVec4): IVec4 =
        IVec4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun plus(scalar: Number): IVec4 =
        IVec4(x + scalar.toInt(), y + scalar.toInt(), z + scalar.toInt(), w + scalar.toInt())

    operator fun minus(other: IVec4): IVec4 =
        IVec4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun minus(scalar: Number): IVec4 =
        IVec4(x - scalar.toInt(), y - scalar.toInt(), z - scalar.toInt(), w - scalar.toInt())

    operator fun unaryMinus(): IVec4 =
        IVec4(-x, -y, -z, -w)

    operator fun times(scalar: Number): IVec4 =
        IVec4(x * scalar.toInt(), y * scalar.toInt(), z * scalar.toInt(), w * scalar.toInt())

    operator fun div(scalar: Number): IVec4 =
        IVec4(x / scalar.toInt(), y / scalar.toInt(), z / scalar.toInt(), w / scalar.toInt())

    infix fun dot(v: IVec4): Double =
        x.toDouble() * v.x.toDouble() + y.toDouble() * v.y.toDouble() + z.toDouble() * v.z.toDouble() + w.toDouble() * v.w.toDouble()

    fun squaredDistanceTo(other: IVec4): Double =
        (other - this).squaredLength

    fun distanceTo(other: IVec4): Double =
        sqrt(squaredDistanceTo(other))
}