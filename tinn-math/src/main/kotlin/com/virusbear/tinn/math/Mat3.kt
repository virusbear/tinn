package com.virusbear.tinn.math

data class Mat3(
    val c0r0: Double = 0.0, val c1r0: Double = 0.0, val c2r0: Double = 0.0,
    val c0r1: Double = 0.0, val c1r1: Double = 0.0, val c2r1: Double = 0.0,
    val c0r2: Double = 0.0, val c1r2: Double = 0.0, val c2r2: Double = 0.0
) {
    companion object {
        val IDENTITY = Mat3(c0r0 = 1.0, c1r1 = 1.0, c2r2 = 1.0)
        val ZERO = Mat3()
    }

    val trace: Double =
        c0r0 + c1r1 + c2r2

    val determinant: Double =
        c0r0 * c1r1 * c2r2 +
        c1r0 * c2r1 * c0r2 +
        c2r0 * c0r1 * c1r2 -
        c2r0 * c1r1 * c0r2 -
        c1r0 * c0r1 * c2r2 -
        c0r0 * c2r1 * c1r2

    val T =
        Mat3(
            c0r0, c0r1, c0r2,
            c1r0, c1r1, c1r2,
            c2r0, c2r1, c2r2
        )

    val inverse: Mat3
        get() {
            require(determinant != 0.0) { "Unable to inverse matrix with determinant=0"}

            val invDet = 1.0 / determinant

            return Mat3(
                c1r1 * c2r2 - c2r1 * c1r2, c2r0 * c1r2 - c1r0 * c2r1, c1r0 * c2r1 - c2r0 * c1r1,
                c2r1 * c0r2 - c0r1 * c2r2, c0r0 * c2r2 - c2r0 * c0r2, c2r0 * c0r1 - c0r0 * c2r1,
                c0r1 * c1r2 - c1r1 * c0r2, c1r0 * c0r2 - c0r0 * c1r2, c0r0 * c1r1 - c1r0 * c0r1
            ) * invDet
        }

    operator fun plus(other: Mat3): Mat3 =
        Mat3(
            c0r0 + other.c0r0, c1r0 + other.c1r0, c2r0 + other.c2r0,
            c0r1 + other.c0r1, c1r1 + other.c1r1, c2r1 + other.c2r1,
            c0r2 + other.c0r2, c1r2 + other.c1r2, c2r2 + other.c2r2
        )

    operator fun minus(other: Mat3): Mat3 =
        Mat3(
            c0r0 - other.c0r0, c1r0 - other.c1r0, c2r0 - other.c2r0,
            c0r1 - other.c0r1, c1r1 - other.c1r1, c2r1 - other.c2r1,
            c0r2 - other.c0r2, c1r2 - other.c1r2, c2r2 - other.c2r2
        )

    operator fun times(scale: Number): Mat3 {
        val value = scale.toDouble()

        return Mat3(
            c0r0 * value, c1r0 * value, c2r0 * value,
            c0r1 * value, c1r1 * value, c2r1 * value,
            c0r2 * value, c1r2 * value, c2r2 * value
        )
    }

    operator fun div(scale: Number): Mat3 {
        val value = scale.toDouble()

        return Mat3(
            c0r0 / value, c1r0 / value, c2r0 / value,
            c0r1 / value, c1r1 / value, c2r1 / value,
            c0r2 / value, c1r2 / value, c2r2 / value
        )
    }

    operator fun times(v: Vec3): Vec3 =
        Vec3(
            v.x * c0r0 + v.y * c1r0 + v.z * c2r0,
            v.x * c0r1 + v.y * c1r1 + v.z * c2r1,
            v.x * c0r2 + v.y * c1r2 + v.z * c2r2
        )

    operator fun times(other: Mat3): Mat3 =
        Mat3(
            c0r0 * other.c0r0 + c1r0 * other.c0r1 + c2r0 * other.c0r2,
            c0r0 * other.c1r0 + c1r0 * other.c1r1 + c2r0 * other.c1r2,
            c0r0 * other.c2r0 + c1r0 * other.c2r1 + c2r0 * other.c2r2,

            c0r1 * other.c0r0 + c1r1 * other.c0r1 + c2r1 * other.c0r2,
            c0r1 * other.c1r0 + c1r1 * other.c1r1 + c2r1 * other.c1r2,
            c0r1 * other.c2r0 + c1r1 * other.c2r1 + c2r1 * other.c2r2,

            c0r2 * other.c0r0 + c1r2 * other.c0r1 + c2r2 * other.c0r2,
            c0r2 * other.c1r0 + c1r2 * other.c1r1 + c2r2 * other.c1r2,
            c0r2 * other.c2r0 + c1r2 * other.c2r1 + c2r2 * other.c2r2
        )
}