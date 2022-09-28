package com.virusbear.tinn.math

import kotlin.math.atan2
import kotlin.math.sqrt

data class Vec2(
    val x: Double,
    val y: Double
) {
    companion object {
        val ZERO = Vec2(0.0, 0.0)
        val ONE = Vec2(1.0, 1.0)
        val UNIT_X = Vec2(1.0, 0.0)
        val UNIT_Y = Vec2(0.0, 1.0)
    }

    val squaredLength: Double =
        x * x + y * y

    val length: Double =
        sqrt(squaredLength)

    val heading: Double =
        atan2(y, x)

    operator fun plus(other: Vec2): Vec2 =
        Vec2(x + other.x, y + other.y)
    operator fun plus(scalar: Number): Vec2 =
        Vec2(x + scalar.toDouble(), y + scalar.toDouble())

    operator fun minus(other: Vec2): Vec2 =
        Vec2(x - other.x, y - other.y)
    operator fun minus(scalar: Number): Vec2 =
        Vec2(x - scalar.toDouble(), y - scalar.toDouble())

    operator fun unaryMinus(): Vec2 =
        Vec2(-x, -y)

    operator fun times(scalar: Number): Vec2 =
        Vec2(x * scalar.toDouble(), y * scalar.toDouble())

    operator fun div(scalar: Number): Vec2 =
        Vec2(x / scalar.toDouble(), y / scalar.toDouble())

    infix fun cross(v: Vec2): Double =
        x * v.y - y * v.x

    infix fun dot(v: Vec2): Double =
        x * v.x + y * v.y

    fun squaredDistanceTo(other: Vec2): Double {
        val dx = other.x - x
        val dy = other.y - y

        return dx * dx + dy * dy
    }

    fun distanceTo(other: Vec2): Double =
        sqrt(squaredDistanceTo(other))
}