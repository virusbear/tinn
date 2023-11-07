package com.virusbear.tinn.ui.compose.modifier

import androidx.compose.runtime.Stable
import com.virusbear.tinn.ui.compose.Measurable
import com.virusbear.tinn.ui.compose.MeasureResult
import com.virusbear.tinn.ui.compose.MeasureScope
import com.virusbear.tinn.ui.compose.androidx.Constraints
import com.virusbear.tinn.ui.compose.androidx.constrainHeight
import com.virusbear.tinn.ui.compose.androidx.constrainWidth

@Stable
fun Modifier.size(
    width: Int,
    height: Int
): Modifier =
    this then SizeModifier(width, height)

@Stable
fun Modifier.fillWidth(): Modifier =
    this then FillSizeModifier(fillWidth = true)

@Stable
fun Modifier.fillHeight(): Modifier =
    this then FillSizeModifier(fillHeight = true)

@Stable
fun Modifier.fillSize(): Modifier =
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
        val placeable = measurable.measure(Constraints.createConstraints(width, constraints.maxWidth, height, constraints.maxHeight))

        return layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}