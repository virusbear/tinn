package com.virusbear.tinn.math.units.dimensions

@JvmInline
value class Inch(val value: Double): Comparable<Inch> {
    operator fun plus(other: Inch): Inch =
        Inch(value = this.value + other.value)

    operator fun minus(other: Inch): Inch =
        Inch(value = this.value + other.value)

    operator fun unaryMinus(): Inch =
        Inch(-value)

    operator fun div(other: Double): Inch =
        Inch(value = this.value / other)

    operator fun div(other: Int): Inch =
        Inch(value = this.value / other)

    operator fun div(other: Inch): Double =
        value / other.value

    operator fun times(other: Double): Inch =
        Inch(value = this.value * other)

    operator fun times(other: Int): Inch =
        Inch(value = this.value * other)

    override fun compareTo(other: Inch): Int =
        value.compareTo(other.value)
}

fun Millimeter.toInch(): Inch =
    Inch(value / 25.4)

val Double.inch: Inch
    get() = Inch(this)

val Int.inch: Inch
    get() = Inch(this.toDouble())

operator fun Double.times(other: Inch): Inch =
    Inch(value = this * other.value)

operator fun Int.times(other: Inch): Inch =
    Inch(value = this.toDouble() * other.value)

fun min(a: Inch, b: Inch): Inch =
    Inch(value = minOf(a.value, b.value))

fun max(a: Inch, b: Inch): Inch =
    Inch(value = maxOf(a.value, b.value))

fun Inch.coerceIn(minimumValue: Inch, maximumValue: Inch): Inch =
    Inch(value = value.coerceIn(minimumValue.value, maximumValue.value))

fun Inch.coerceAtLeast(minimumValue: Inch): Inch =
    Inch(value = value.coerceAtLeast(minimumValue.value))

fun Inch.coerceAtMost(maximumValue: Inch): Inch =
    Inch(value = value.coerceAtMost(maximumValue.value))