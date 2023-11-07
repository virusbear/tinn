package com.virusbear.tinn.ui.compose

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
fun Density(density: Double): Density =
    DensityImpl(density)

private data class DensityImpl(
    override val density: Double
): Density

@Immutable
interface Density {
    @Stable
    val density: Double

    @Stable
    fun Dp.toPx(): Double = value * density

    @Stable
    fun Int.toDp(): Dp = (this / density).dp

    @Stable
    fun Double.toDp(): Dp = (this / density).dp
}