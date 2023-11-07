package com.virusbear.tinn.ui.compose

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
fun interface Alignment {
    fun align(size: IntSize, space: IntSize): IntOffset

    @Stable
    fun interface Horizontal {
        fun align(size: Int, space: Int): Int
    }

    @Stable
    fun interface Vertical {
        fun align(size: Int, space: Int): Int
    }

    companion object {
        @Stable
        val TopStart: Alignment = BiasAlignment(-1, 1)
        @Stable
        val TopCenter: Alignment = BiasAlignment(0, -1)
        @Stable
        val TopEnd: Alignment = BiasAlignment(1, -1)
        @Stable
        val CenterStart: Alignment = BiasAlignment(-1, 0)
        @Stable
        val Center: Alignment = BiasAlignment(0, 0)
        @Stable
        val CenterEnd: Alignment = BiasAlignment(1, 0)
        @Stable
        val BottomStart: Alignment = BiasAlignment(-1, -1)
        @Stable
        val BottomCenter: Alignment = BiasAlignment(0, -1)
        @Stable
        val BottomEnd: Alignment = BiasAlignment(1, -1)

        @Stable
        val Top: Vertical = BiasAlignment.Vertical(-1)
        @Stable
        val CenterVertically: Vertical = BiasAlignment.Vertical(0)
        @Stable
        val Bottom: Vertical = BiasAlignment.Vertical(1)

        @Stable
        val Start: Horizontal = BiasAlignment.Horizontal(-1)
        @Stable
        val CenterHorizontally: Horizontal = BiasAlignment.Horizontal(0)
        @Stable
        val End: Horizontal = BiasAlignment.Horizontal(1)
    }
}

@Immutable
internal data class BiasAlignment(
    private val horizontalBias: Int,
    private val verticalBias: Int
): Alignment {
    override fun align(size: IntSize, space: IntSize): IntOffset {
        val center = (space - size) / 2

        return IntOffset(
            x = center.x * (1 + horizontalBias),
            y = center.y * (1 + verticalBias)
        )
    }

    @Immutable
    internal data class Horizontal(
        private val bias: Int
    ): Alignment.Horizontal {
        override fun align(size: Int, space: Int): Int {
            val center = (space - size) / 2

            return (center * (1 + bias))
        }
    }

    @Immutable
    internal data class Vertical(
        private val bias: Int
    ): Alignment.Vertical {
        override fun align(size: Int, space: Int): Int {
            val center = (space - size) / 2

            return (center * (1 + bias))
        }
    }
}