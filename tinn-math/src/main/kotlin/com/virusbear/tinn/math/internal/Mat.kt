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
        val result = create()

        for(i in matrix.indices) {
            result.matrix[i] = matrix[i] + other.matrix[i]
        }

        return result
    }

    operator fun minus(other: T): T {
        val result = create()

        for(i in matrix.indices) {
            result.matrix[i] = matrix[i] - other.matrix[i]
        }

        return result
    }

    operator fun times(other: T): T {
        val result = create()

        for(c in 0 until dim) {
            for(r in 0 until dim) {
                var value = 0.0

                for(n in 0 until dim) {
                    value += this[n, r] * other[c, n]
                }

                result[c, r] = value
            }
        }

        return result
    }

    operator fun times(scale: Number): T {
        val value = scale.toDouble()
        val result = create()

        for(i in matrix.indices) {
            result.matrix[i] = matrix[i] * value
        }

        return result
    }

    operator fun div(scale: Number): T =
        this * (1.0 / scale.toDouble())

    //TODO: How to implement multiplication with vector with this base implementation?
    //TODO: How to define static INDENTITY & ZERO constants

    val T: T by lazy { transpose() }
    val trace: Double by lazy { trace() }
    val determinant: Double by lazy { determinant() }
    val inverse: T by lazy { invert() }

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

    private fun determinant(): Double =
        determinant(matrix, 0, 0, dim)

    private fun determinant(matrix: DoubleArray, cOffset: Int, rOffset: Int, dim: Int): Double {
        if(dim == 1) {
            return matrix[0]
        }

        var determinant = 0.0
        var sign = 1

        val temp = DoubleArray((dim - 1) * (dim - 1))

        for(n in 0 until dim) {
            calculateCofactor(matrix, 0, n, dim, temp)
            val c = cOffset % dim
            val r = (rOffset + n) % dim
            determinant += sign * matrix[r * dim + c] * determinant(temp, cOffset + 1, (rOffset + (n + 1) % dim), dim - 1)

            sign = -sign
        }

        return determinant
    }

    private fun calculateCofactor(matrix: DoubleArray, cOffset: Int, rOffset: Int, dim: Int, cofactor: DoubleArray) {
        var i = 0

        for(r in 0 until dim) {
            for(c in 0 until dim) {
                if(c != cOffset && r != rOffset) {
                    cofactor[i++] = matrix[r * dim + c]
                }
            }
        }
    }

    private fun invert(): T {
        require(determinant != 0.0) { "Unable to inverse matrix with determinant=0" }

        val invDet = 1.0 / determinant

        TODO("Implement value generation and multiplication with invDet")
    }
}

class Mat4(
    matrix: DoubleArray
): Mat<Mat4>(3, matrix) {
    constructor(matrix: Mat4): this(matrix.matrix.clone())
    constructor(): this(DoubleArray(9))

    override fun create(): Mat4 =
        create(DoubleArray(9))

    override fun create(matrix: DoubleArray): Mat4 =
        Mat4(matrix)
}


fun main() {
    println(Mat4(doubleArrayOf(5.0, 3.0, 7.0, 2.0, 4.0, 9.0, 3.0, 6.0, 4.0)).determinant)
}