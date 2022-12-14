package com.virusbear.tinn.math

data class Mat4(
    val c0r0: Double = 0.0, val c1r0: Double = 0.0, val c2r0: Double = 0.0, val c3r0: Double = 0.0,
    val c0r1: Double = 0.0, val c1r1: Double = 0.0, val c2r1: Double = 0.0, val c3r1: Double = 0.0,
    val c0r2: Double = 0.0, val c1r2: Double = 0.0, val c2r2: Double = 0.0, val c3r2: Double = 0.0,
    val c0r3: Double = 0.0, val c1r3: Double = 0.0, val c2r3: Double = 0.0, val c3r3: Double = 0.0,
) {
    companion object {
        val IDENTITY = Mat4(c0r0 = 1.0, c1r1 = 1.0, c2r2 = 1.0, c3r3 = 1.0)
        val ZERO = Mat4()
    }

    val trace: Double =
        c0r0 + c1r1 + c2r2 + c3r3

    val determinant: Double =
        c0r3 * c1r2 * c2r1 * c3r0 -
        c0r2 * c1r3 * c2r1 * c3r0 -
        c0r3 * c1r1 * c2r2 * c3r0 +
        c0r1 * c1r3 * c2r2 * c3r0 +
        c0r2 * c1r1 * c2r3 * c3r0 -
        c0r1 * c1r2 * c2r3 * c3r0 -
        c0r3 * c1r2 * c2r0 * c3r1 +
        c0r2 * c1r3 * c2r0 * c3r1 +
        c0r3 * c1r0 * c2r2 * c3r1 -
        c0r0 * c1r3 * c2r2 * c3r1 -
        c0r2 * c1r0 * c2r3 * c3r1 +
        c0r0 * c1r2 * c2r3 * c3r1 +
        c0r3 * c1r1 * c2r0 * c3r2 -
        c0r1 * c1r3 * c2r0 * c3r2 -
        c0r3 * c1r0 * c2r1 * c3r2 +
        c0r0 * c1r3 * c2r1 * c3r2 +
        c0r1 * c1r0 * c2r3 * c3r2 -
        c0r0 * c1r1 * c2r3 * c3r2 -
        c0r2 * c1r1 * c2r0 * c3r3 +
        c0r1 * c1r2 * c2r0 * c3r3 +
        c0r2 * c1r0 * c2r1 * c3r3 -
        c0r0 * c1r2 * c2r1 * c3r3 -
        c0r1 * c1r0 * c2r2 * c3r3 +
        c0r0 * c1r1 * c2r2 * c3r3

    val T =
        Mat4(
            c0r0, c0r1, c0r2, c0r3,
            c1r0, c1r1, c1r2, c1r3,
            c2r0, c2r1, c2r2, c2r3,
            c3r0, c3r1, c3r2, c3r3
        )

    val inverse: Mat4
        get() {
            if (this == IDENTITY) {
                return this
            }

            require(determinant != 0.0) { "Unable to inverse matrix with determinant=0"}

            val invDet = 1.0 / determinant

            val n00 = c1r2 * c2r3 * c3r1 - c1r3 * c2r2 * c3r1 + c1r3 * c2r1 * c3r2 - c1r1 * c2r3 * c3r2 - c1r2 * c2r1 * c3r3 + c1r1 * c2r2 * c3r3
            val n01 = c0r3 * c2r2 * c3r1 - c0r2 * c2r3 * c3r1 - c0r3 * c2r1 * c3r2 + c0r1 * c2r3 * c3r2 + c0r2 * c2r1 * c3r3 - c0r1 * c2r2 * c3r3
            val n02 = c0r2 * c1r3 * c3r1 - c0r3 * c1r2 * c3r1 + c0r3 * c1r1 * c3r2 - c0r1 * c1r3 * c3r2 - c0r2 * c1r1 * c3r3 + c0r1 * c1r2 * c3r3
            val n03 = c0r3 * c1r2 * c2r1 - c0r2 * c1r3 * c2r1 - c0r3 * c1r1 * c2r2 + c0r1 * c1r3 * c2r2 + c0r2 * c1r1 * c2r3 - c0r1 * c1r2 * c2r3
            val n10 = c1r3 * c2r2 * c3r0 - c1r2 * c2r3 * c3r0 - c1r3 * c2r0 * c3r2 + c1r0 * c2r3 * c3r2 + c1r2 * c2r0 * c3r3 - c1r0 * c2r2 * c3r3
            val n11 = c0r2 * c2r3 * c3r0 - c0r3 * c2r2 * c3r0 + c0r3 * c2r0 * c3r2 - c0r0 * c2r3 * c3r2 - c0r2 * c2r0 * c3r3 + c0r0 * c2r2 * c3r3
            val n12 = c0r3 * c1r2 * c3r0 - c0r2 * c1r3 * c3r0 - c0r3 * c1r0 * c3r2 + c0r0 * c1r3 * c3r2 + c0r2 * c1r0 * c3r3 - c0r0 * c1r2 * c3r3
            val n13 = c0r2 * c1r3 * c2r0 - c0r3 * c1r2 * c2r0 + c0r3 * c1r0 * c2r2 - c0r0 * c1r3 * c2r2 - c0r2 * c1r0 * c2r3 + c0r0 * c1r2 * c2r3
            val n20 = c1r1 * c2r3 * c3r0 - c1r3 * c2r1 * c3r0 + c1r3 * c2r0 * c3r1 - c1r0 * c2r3 * c3r1 - c1r1 * c2r0 * c3r3 + c1r0 * c2r1 * c3r3
            val n21 = c0r3 * c2r1 * c3r0 - c0r1 * c2r3 * c3r0 - c0r3 * c2r0 * c3r1 + c0r0 * c2r3 * c3r1 + c0r1 * c2r0 * c3r3 - c0r0 * c2r1 * c3r3
            val n22 = c0r1 * c1r3 * c3r0 - c0r3 * c1r1 * c3r0 + c0r3 * c1r0 * c3r1 - c0r0 * c1r3 * c3r1 - c0r1 * c1r0 * c3r3 + c0r0 * c1r1 * c3r3
            val n23 = c0r3 * c1r1 * c2r0 - c0r1 * c1r3 * c2r0 - c0r3 * c1r0 * c2r1 + c0r0 * c1r3 * c2r1 + c0r1 * c1r0 * c2r3 - c0r0 * c1r1 * c2r3
            val n30 = c1r2 * c2r1 * c3r0 - c1r1 * c2r2 * c3r0 - c1r2 * c2r0 * c3r1 + c1r0 * c2r2 * c3r1 + c1r1 * c2r0 * c3r2 - c1r0 * c2r1 * c3r2
            val n31 = c0r1 * c2r2 * c3r0 - c0r2 * c2r1 * c3r0 + c0r2 * c2r0 * c3r1 - c0r0 * c2r2 * c3r1 - c0r1 * c2r0 * c3r2 + c0r0 * c2r1 * c3r2
            val n32 = c0r2 * c1r1 * c3r0 - c0r1 * c1r2 * c3r0 - c0r2 * c1r0 * c3r1 + c0r0 * c1r2 * c3r1 + c0r1 * c1r0 * c3r2 - c0r0 * c1r1 * c3r2
            val n33 = c0r1 * c1r2 * c2r0 - c0r2 * c1r1 * c2r0 + c0r2 * c1r0 * c2r1 - c0r0 * c1r2 * c2r1 - c0r1 * c1r0 * c2r2 + c0r0 * c1r1 * c2r2

            return Mat4(
                n00, n10, n20, n30,
                n01, n11, n21, n31,
                n02, n12, n22, n32,
                n03, n13, n23, n33
            ) * invDet
        }

    operator fun plus(other: Mat4): Mat4 =
        Mat4(
            c0r0 + other.c0r0, c1r0 + other.c1r0, c2r0 + other.c2r0, c3r0 + other.c3r0,
            c0r1 + other.c0r1, c1r1 + other.c1r1, c2r1 + other.c2r1, c3r1 + other.c3r1,
            c0r2 + other.c0r2, c1r2 + other.c1r2, c2r2 + other.c2r2, c3r2 + other.c3r2,
            c0r3 + other.c0r3, c1r3 + other.c1r3, c2r3 + other.c2r3, c3r3 + other.c3r3
        )

    operator fun minus(other: Mat4): Mat4 =
        Mat4(
            c0r0 - other.c0r0, c1r0 - other.c1r0, c2r0 - other.c2r0, c3r0 - other.c3r0,
            c0r1 - other.c0r1, c1r1 - other.c1r1, c2r1 - other.c2r1, c3r1 - other.c3r1,
            c0r2 - other.c0r2, c1r2 - other.c1r2, c2r2 - other.c2r2, c3r2 - other.c3r2,
            c0r3 - other.c0r3, c1r3 - other.c1r3, c2r3 - other.c2r3, c3r3 - other.c3r3
        )

    operator fun times(scale: Number): Mat4 {
        val value = scale.toDouble()

        return Mat4(
            c0r0 * value, c1r0 * value, c2r0 * value, c3r0 * value,
            
        )
    }
}