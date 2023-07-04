package com.virusbear.tinn.math

import kotlin.math.sqrt

data class Vec4(
    val x: Double,
    val y: Double,
    val z: Double,
    val w: Double
) {
    companion object {
        val ZERO = Vec4(0.0, 0.0, 0.0, 0.0)
        val ONE = Vec4(1.0, 1.0, 1.0, 1.0)
        val UNIT_X = Vec4(1.0, 0.0, 0.0, 0.0)
        val UNIT_Y = Vec4(0.0, 1.0, 0.0, 0.0)
        val UNIT_Z = Vec4(0.0, 0.0, 1.0, 0.0)
        val UNIT_W = Vec4(0.0, 0.0, 0.0, 1.0)
    }

    val squaredLength: Double =
        x * x + y * y + z * z + w * w

    val length by lazy {
        sqrt(squaredLength)
    }

    operator fun plus(other: Vec4): Vec4 =
        Vec4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun plus(scalar: Number): Vec4 =
        Vec4(x + scalar.toDouble(), y + scalar.toDouble(), z + scalar.toDouble(), w + scalar.toDouble())

    operator fun minus(other: Vec4): Vec4 =
        Vec4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun minus(scalar: Number): Vec4 =
        Vec4(x - scalar.toDouble(), y - scalar.toDouble(), z - scalar.toDouble(), w - scalar.toDouble())

    operator fun unaryMinus(): Vec4 =
        Vec4(-x, -y, -z, -w)

    operator fun times(scalar: Number): Vec4 =
        Vec4(x * scalar.toDouble(), y * scalar.toDouble(), z * scalar.toDouble(), w * scalar.toDouble())

    operator fun div(scalar: Number): Vec4 =
        Vec4(x / scalar.toDouble(), y / scalar.toDouble(), z / scalar.toDouble(), w / scalar.toDouble())

    infix fun dot(v: Vec4): Double =
        x * v.x + y * v.y + z * v.z + w * v.w

    fun squaredDistanceTo(other: Vec4): Double =
        (other - this).squaredLength

    fun distanceTo(other: Vec4): Double =
        sqrt(squaredDistanceTo(other))
}