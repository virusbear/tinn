package com.virusbear.tinn.ui.compose.modifier

import androidx.compose.runtime.Stable
import com.virusbear.tinn.ui.compose.*
import kotlin.math.roundToInt

@Stable
fun Modifier.padding(
    start: Dp = Dp.Unspecified,
    top: Dp = Dp.Unspecified,
    end: Dp = Dp.Unspecified,
    bottom: Dp = Dp.Unspecified
): Modifier =
    this then PaddingModifier(
        start = start,
        top = top,
        end = end,
        bottom = bottom
    )

@Stable
fun Modifier.padding(
    horizontal: Dp = Dp.Unspecified,
    vertical: Dp = Dp.Unspecified
): Modifier = padding(
    start = horizontal,
    top = vertical,
    end = horizontal,
    bottom = vertical
)

@Stable
fun Modifier.padding(
    all: Dp = Dp.Unspecified
): Modifier = padding(
    horizontal = all,
    vertical = all
)

private class PaddingModifier(
    val start: Dp = Dp.Unspecified,
    val top: Dp = Dp.Unspecified,
    val end: Dp = Dp.Unspecified,
    val bottom: Dp = Dp.Unspecified
): LayoutModifier {
    init {
        require(
            (start.value >= 0f || start == Dp.Unspecified) &&
                    (top.value >= 0f || top == Dp.Unspecified) &&
                    (end.value >= 0f || end == Dp.Unspecified) &&
                    (bottom.value >= 0f || bottom == Dp.Unspecified)
        ) {
            "Padding must be specified"
        }
    }

    override fun MeasureScope.measure(constraints: Constraints, measurable: Measurable): MeasureResult {
        val horizontal = start + end
        val vertical = top + bottom

        val placeable = measurable.measure(constraints.offset(-horizontal.roundToPx(), -vertical.roundToPx()))

        val width = placeable.width + horizontal.toPx()
        val height = placeable.height + vertical.toPx()

        return layout(width.roundToInt(), height.roundToInt()) {
            placeable.place(start.roundToPx(), top.roundToPx())
        }
    }
}
