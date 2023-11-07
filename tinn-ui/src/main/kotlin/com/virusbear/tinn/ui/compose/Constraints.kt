package com.virusbear.tinn.ui.compose

import androidx.compose.runtime.Stable

@Stable
data class Constraints(
    val minWidth: Int = 0,
    val minHeight: Int = 0,
    val maxWidth: Int = Infinity,
    val maxHeight: Int = Infinity
) {
    init {
        require(maxWidth >= minWidth) {
            "maxWidth($maxWidth) must be >= minWidth($minWidth)"
        }
        require(maxHeight >= minHeight) {
            "maxHeight($maxHeight) must be >= minHeight($minHeight)"
        }
        require(minWidth >= 0 && minHeight >= 0) {
            "minWidth($minWidth) and minHeight($minHeight) must be >= 0"
        }
    }

    val hasBoundedWidth: Boolean = maxWidth != Infinity
    val hasBoundedHeight: Boolean = maxHeight != Infinity
    val hasFixedWidth: Boolean = minWidth == maxWidth
    val hadFixedHeight: Boolean = minHeight == maxHeight
    val isZero: Boolean = maxWidth == 0 || maxHeight == 0

    companion object {
        const val Infinity = Int.MAX_VALUE

        @Stable
        fun fixed(
            width: Int,
            height: Int
        ): Constraints {
            require(width >= 0 && height >= 0) {
                "width($width) and height($height) must be >= 0"
            }

            return Constraints(width, width, height, height)
        }

        @Stable
        fun fixedWidth(
            width: Int
        ): Constraints {
            require(width >= 0) {
                "width($width) must be >= 0"
            }

            return Constraints(width, width, 0, Infinity)
        }

        @Stable
        fun fixedHeight(
            height: Int
        ): Constraints {
            require(height >= 0) {
                "height($height) must be >= 0"
            }

            return Constraints(0, Infinity, height, height)
        }
    }
}

@Stable
fun Constraints.constrain(other: Constraints): Constraints =
    Constraints(
        minWidth = other.minWidth.coerceIn(minWidth, maxWidth),
        minHeight = other.minHeight.coerceIn(minHeight, maxHeight),
        maxWidth = other.maxWidth.coerceIn(minWidth, maxWidth),
        maxHeight = other.maxHeight.coerceIn(minHeight, maxHeight)
    )

@Stable
fun Constraints.constrain(size: IntSize): IntSize =
    IntSize(
        x = size.x.coerceIn(minWidth, maxWidth),
        y = size.y.coerceIn(minHeight, maxHeight)
    )

@Stable
fun Constraints.constrainWidth(width: Int): Int =
    width.coerceIn(minWidth, maxWidth)

@Stable
fun Constraints.constrainHeight(height: Int): Int =
    height.coerceIn(minHeight, maxHeight)

@Stable
fun Constraints.offset(horizontal: Int, vertical: Int): Constraints =
    Constraints(
        minWidth = (minWidth + horizontal).coerceAtLeast(0),
        maxWidth = if(maxWidth == Constraints.Infinity) Constraints.Infinity else (maxWidth + horizontal).coerceAtLeast(0),
        minHeight = (minHeight + vertical).coerceAtLeast(0),
        maxHeight = if(maxHeight == Constraints.Infinity) Constraints.Infinity else (maxHeight + vertical).coerceAtLeast(0)
    )
