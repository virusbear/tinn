package com.virusbear.tinn.math

import kotlin.math.sqrt

data class IVec3(
    val x: Int,
    val y: Int,
    val z: Int,
) {
    companion object {
        val ZERO = IVec3(0, 0, 0)
        val ONE = IVec3(1, 1, 0)
        val UNIT_X = IVec3(1, 0, 0)
        val UNIT_Y = IVec3(0, 1, 0)
        val UNIT_Z = IVec3(0, 0, 1)
    }

    val squaredLength: Int =
        x * x + y * y + z * z

    val length: Double =
        sqrt(squaredLength.toDouble())

    operator fun plus(other: IVec3): IVec3 =
        IVec3(x + other.x, y + other.y, z + other.z)
    operator fun plus(scalar: Number): IVec3 =
        IVec3(x + scalar.toInt(), y + scalar.toInt(), z + scalar.toInt())

    operator fun minus(other: IVec3): IVec3 =
        IVec3(x - other.x, y - other.y, z - other.z)
    operator fun minus(scalar: Number): IVec3 =
        IVec3(x - scalar.toInt(), y - scalar.toInt(), z - scalar.toInt())

    operator fun unaryMinus(): IVec3 =
        IVec3(-x, -y, -z)

    operator fun times(scalar: Number): IVec3 =
        IVec3(x * scalar.toInt(), y * scalar.toInt(), z * scalar.toInt())

    operator fun div(scalar: Number): IVec3 =
        IVec3(x / scalar.toInt(), y / scalar.toInt(), z / scalar.toInt())

    infix fun cross(v: IVec3): IVec3 =
        IVec3(
            y * v.z - z * v.y,
            z * v.x - x * v.z,
            x * v.y - y * v.x
        )

    infix fun dot(v: IVec3): Int =
        x * v.x + y * v.y + z * v.z

    fun squaredDistanceTo(other: IVec3): Int {
        val dx = other.x - x
        val dy = other.y - y
        val dz = other.z - z

        return dx * dx + dy * dy + dz * dz
    }

    fun distanceTo(other: IVec3): Double =
        sqrt(squaredDistanceTo(other).toDouble())
}