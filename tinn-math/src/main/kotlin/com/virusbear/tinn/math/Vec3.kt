package com.virusbear.tinn.math

import kotlin.math.sqrt

data class Vec3(
    val x: Double,
    val y: Double,
    val z: Double
) {
    companion object {
        val ZERO = Vec3(0.0, 0.0, 0.0)
        val ONE = Vec3(1.0, 1.0, 0.0)
        val UNIT_X = Vec3(1.0, 0.0, 0.0)
        val UNIT_Y = Vec3(0.0, 1.0, 0.0)
        val UNIT_Z = Vec3(0.0, 0.0, 1.0)
    }

    val squaredLength: Double =
        x * x + y * y + z * z

    val length: Double =
        sqrt(squaredLength)

    operator fun plus(other: Vec3): Vec3 =
        Vec3(x + other.x, y + other.y, z + other.z)
    operator fun plus(scalar: Number): Vec3 =
        Vec3(x + scalar.toDouble(), y + scalar.toDouble(), z + scalar.toDouble())

    operator fun minus(other: Vec3): Vec3 =
        Vec3(x - other.x, y - other.y, z - other.z)
    operator fun minus(scalar: Number): Vec3 =
        Vec3(x - scalar.toDouble(), y - scalar.toDouble(), z - scalar.toDouble())

    operator fun unaryMinus(): Vec3 =
        Vec3(-x, -y, -z)

    operator fun times(scalar: Number): Vec3 =
        Vec3(x * scalar.toDouble(), y * scalar.toDouble(), z * scalar.toDouble())

    operator fun div(scalar: Number): Vec3 =
        Vec3(x / scalar.toDouble(), y / scalar.toDouble(), z / scalar.toDouble())

    infix fun cross(v: Vec3): Vec3 =
        Vec3(
            y * v.z - z * v.y,
            z * v.x - x * v.z,
            x * v.y - y * v.x
        )

    infix fun dot(v: Vec3): Double =
        x * v.x + y * v.y + z * v.z

    fun squaredDistanceTo(other: Vec3): Double =
        (other - this).squaredLength

    fun distanceTo(other: Vec3): Double =
        sqrt(squaredDistanceTo(other))
}