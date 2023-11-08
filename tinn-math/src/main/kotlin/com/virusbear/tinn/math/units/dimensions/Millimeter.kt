package com.virusbear.tinn.math.units.dimensions

@JvmInline
value class Millimeter(val value: Double): Comparable<Millimeter> {
    operator fun plus(other: Millimeter): Millimeter =
        Millimeter(value = this.value + other.value)

    operator fun minus(other: Millimeter): Millimeter =
        Millimeter(value = this.value + other.value)

    operator fun unaryMinus(): Millimeter =
        Millimeter(-value)

    operator fun div(other: Double): Millimeter =
        Millimeter(value = this.value / other)

    operator fun div(other: Int): Millimeter =
        Millimeter(value = this.value / other)

    operator fun div(other: Millimeter): Double =
        value / other.value

    operator fun times(other: Double): Millimeter =
        Millimeter(value = this.value * other)

    operator fun times(other: Int): Millimeter =
        Millimeter(value = this.value * other)

    override fun compareTo(other: Millimeter): Int =
        value.compareTo(other.value)
}

fun Inch.toMillimeter(): Millimeter =
    Millimeter(value * 25.4)

val Double.mm: Millimeter
    get() = Millimeter(this)

val Int.mm: Millimeter
    get() = Millimeter(this.toDouble())

operator fun Double.times(other: Millimeter): Millimeter =
    Millimeter(value = this * other.value)

operator fun Int.times(other: Millimeter): Millimeter =
    Millimeter(value = this.toDouble() * other.value)

fun min(a: Millimeter, b: Millimeter): Millimeter =
    Millimeter(value = minOf(a.value, b.value))

fun max(a: Millimeter, b: Millimeter): Millimeter =
    Millimeter(value = maxOf(a.value, b.value))

fun Millimeter.coerceIn(minimumValue: Millimeter, maximumValue: Millimeter): Millimeter =
    Millimeter(value = value.coerceIn(minimumValue.value, maximumValue.value))

fun Millimeter.coerceAtLeast(minimumValue: Millimeter): Millimeter =
    Millimeter(value = value.coerceAtLeast(minimumValue.value))

fun Millimeter.coerceAtMost(maximumValue: Millimeter): Millimeter =
    Millimeter(value = value.coerceAtMost(maximumValue.value))