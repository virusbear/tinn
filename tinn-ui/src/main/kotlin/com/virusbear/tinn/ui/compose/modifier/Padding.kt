package com.virusbear.tinn.ui.compose.modifier

import androidx.compose.runtime.Stable
import com.virusbear.tinn.ui.compose.*

@Stable
fun Modifier.padding(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
): Modifier = this.then(
    PaddingModifier(
        left = left,
        top = top,
        right = right,
        bottom = bottom
    )
)

@Stable
fun Modifier.padding(
    horizontal: Int = 0,
    vertical: Int = 0
): Modifier = padding(
    left = horizontal,
    top = vertical,
    right = horizontal,
    bottom = vertical
)

@Stable
fun Modifier.padding(
    all: Int = 0
): Modifier = padding(
    horizontal = all,
    vertical = all
)

private class PaddingModifier(
    val left: Int = 0,
    val top: Int = 0,
    val right: Int = 0,
    val bottom: Int = 0
): LayoutModifier {
    init {
        require(left >= 0 && top >= 0f && right >= 0f && bottom >= 0f) {
            "Padding must be non-negative"
        }
    }

    override fun MeasureScope.measure(constraints: Constraints, measurable: Measurable): MeasureResult {
        val horizontal = left + right
        val vertical = top + bottom

        val placeable = measurable.measure(constraints)

        val width = placeable.width + horizontal
        val height = placeable.height + vertical

        return layout(width, height) {
            placeable.place(left, top)
        }
    }
}
