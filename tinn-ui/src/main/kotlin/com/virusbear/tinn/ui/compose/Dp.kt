@file:Suppress("NOTHING_TO_INLINE")

package com.virusbear.tinn.ui.compose

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

//TODO: add Density class

@JvmInline
@Immutable
value class Dp(
    val value: Double
): Comparable<Dp> {
    @Stable
    inline operator fun plus(other: Dp): Dp =
        Dp(value = this.value + other.value)

    @Stable
    inline operator fun minus(other: Dp): Dp =
        Dp(value = this.value + other.value)

    @Stable
    inline operator fun unaryMinus(): Dp =
        Dp(-value)

    @Stable
    inline operator fun div(other: Double): Dp =
        Dp(value = this.value / other)

    @Stable
    inline operator fun div(other: Int): Dp =
        Dp(value = this.value / other)

    @Stable
    inline operator fun div(other: Dp): Double =
        value / other.value

    @Stable
    inline operator fun times(other: Double): Dp =
        Dp(value = this.value * other)

    @Stable
    inline operator fun times(other: Int): Dp =
        Dp(value = this.value * other)

    override fun compareTo(other: Dp): Int =
        value.compareTo(other.value)

    @Stable
    override fun toString(): String =
        if(isUnspecified) {
            "Dp.Unspecified"
        } else {
            "$value.dp"
        }

    companion object {
        @Stable
        val Hairline = Dp(value = 0.0)
        @Stable
        val Infinity = Dp(value = Double.POSITIVE_INFINITY)
        @Stable
        val Unspecified = Dp(value = Double.NaN)

        @Stable
        val ReferenceDensity = Density(160.0)
    }
}

@Stable
inline val Dp.isSpecified: Boolean
    get() = !value.isNaN()

@Stable
inline val Dp.isUnspecified: Boolean
    get() = value.isNaN()

@Stable
inline val Dp.isFinite: Boolean
    get() = value != Double.POSITIVE_INFINITY

@Stable
inline val Double.dp: Dp
    get() = Dp(value = this)

@Stable
inline val Int.dp: Dp
    get() = Dp(value = this.toDouble())

inline fun Dp.takeOrElse(block: () -> Dp): Dp =
    if(isSpecified) this else block()

@Stable
inline operator fun Double.times(other: Dp): Dp =
    Dp(value = this * other.value)

@Stable
inline operator fun Int.times(other: Dp): Dp =
    Dp(value = this.toDouble() * other.value)

@Stable
inline fun min(a: Dp, b: Dp): Dp =
    Dp(value = minOf(a.value, b.value))

@Stable
inline fun max(a: Dp, b: Dp): Dp =
    Dp(value = maxOf(a.value, b.value))

@Stable
inline fun Dp.coerceIn(minimumValue: Dp, maximumValue: Dp): Dp =
    Dp(value = value.coerceIn(minimumValue.value, maximumValue.value))

@Stable
inline fun Dp.coerceAtLeast(minimumValue: Dp): Dp =
    Dp(value = value.coerceAtLeast(minimumValue.value))

@Stable
inline fun Dp.coerceAtMost(maximumValue: Dp): Dp =
    Dp(value = value.coerceAtMost(maximumValue.value))