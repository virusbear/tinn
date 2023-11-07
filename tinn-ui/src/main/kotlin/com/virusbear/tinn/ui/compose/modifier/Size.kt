package com.virusbear.tinn.ui.compose.modifier

import androidx.compose.runtime.Stable
import com.virusbear.tinn.ui.compose.*

@Stable
fun Modifier.width(width: Int) =
    this then SizeModifier(
        minWidth = width,
        maxWidth = width
    )

private class SizeModifier(
    private val minWidth: Int,
    private val minHeight: Int,
    private val maxWidth: Int,
    private val maxHeight: Int
): LayoutModifier {
    override fun MeasureScope.measure(constraints: Constraints, measurable: Measurable): MeasureResult {
        TODO("Not yet implemented")
    }

}


















@Stable
fun Modifier.size(
    width: Int,
    height: Int
): Modifier =
    this then SizeModifier(width, height)

@Stable
fun Modifier.fillMaxWidth(): Modifier =
    this then FillSizeModifier(fillWidth = true)

@Stable
fun Modifier.fillMaxHeight(): Modifier =
    this then FillSizeModifier(fillHeight = true)

@Stable
fun Modifier.fillMaxSize(): Modifier =
    this then FillSizeModifier(fillWidth = true, fillHeight = true)

@Stable
private class SizeModifier(
    val width: Int,
    val height: Int
): LayoutModifier {
    init {
        require(width > 0 && height > 0) {
            "Size must be non-negative"
        }
    }

    override fun MeasureScope.measure(constraints: Constraints, measurable: Measurable): MeasureResult {
        val placeable = measurable.measure(constraints)
        val width = constraints.constrainWidth(width)
        val height = constraints.constrainHeight(height)

        return layout(width, height) {
            placeable.place(0, 0)
        }
    }
}

@Stable
private class FillSizeModifier(
    val fillWidth: Boolean = false,
    val fillHeight: Boolean = false
): LayoutModifier {
    override fun MeasureScope.measure(constraints: Constraints, measurable: Measurable): MeasureResult {
        val width = if(fillWidth) constraints.maxWidth else constraints.minWidth
        val height = if(fillHeight) constraints.maxHeight else constraints.minHeight
        val placeable = measurable.measure(Constraints(width, constraints.maxWidth, height, constraints.maxHeight))

        return layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}