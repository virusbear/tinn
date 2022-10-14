package com.virusbear.tinn.math.internal

abstract class Mat<T: Mat<T>>(
    private val dim: Int,
    protected val matrix: DoubleArray
) {
    operator fun get(c: Int, r: Int): Double =
        matrix[r * dim + c]

    private operator fun set(c: Int, r: Int, value: Double) {
        matrix[r * dim + c] = value
    }

    operator fun plus(other: T): T {
        TODO("Not yet implemented")
    }

    operator fun minus(other: T): T {
        TODO("Not yet implemented")
    }

    operator fun times(other: T): T {
        TODO("Not yet implemented")
    }

    operator fun times(scale: Number): T {
        TODO("Not yet implemented")
    }

    operator fun div(scale: Number): T =
        this * (1.0 / scale.toDouble())

    //TODO: How to implement multiplication with vector with this base implementation?
    //TODO: How to define static INDENTITY & ZERO constants

    val T: T = transpose()
    val trace: Double = trace()
    val determinant: Double = determinant()
    val inverse: T = invert()

    protected abstract fun create(): T
    protected abstract fun create(matrix: DoubleArray): T

    private fun transpose(): T {
        val result = create()

        for(r in 0 until dim) {
            for(c in 0 until dim) {
                result[c, r] = this[r, c]
            }
        }

        return result
    }

    private fun trace(): Double {
        var result = 0.0

        for(i in 0 until dim) {
            result += this[i, i]
        }

        return result
    }

    private fun determinant(): Double {
        TODO("Not yet implemented")
    }

    private fun invert(): T {
        require(determinant != 0.0) { "Unable to inverse matrix with determinant=0" }

        val invDet = 1.0 / determinant

        TODO("Implement value generation and multiplication with invDet")
    }
}

class Mat4 private constructor(
    matrix: DoubleArray
): Mat<Mat4>(4, matrix) {
    constructor(matrix: Mat4): this(matrix.matrix.clone())
    constructor(): this(DoubleArray(16))

    override fun create(): Mat4 =
        create(DoubleArray(16))

    override fun create(matrix: DoubleArray): Mat4 =
        Mat4(matrix)
}