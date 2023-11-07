package com.virusbear.tinn.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.virusbear.tinn.ui.compose.androidx.Constraints
import com.virusbear.tinn.ui.compose.androidx.constrainHeight
import com.virusbear.tinn.ui.compose.androidx.constrainWidth
import com.virusbear.tinn.ui.compose.modifier.Modifier
import com.virusbear.tinn.ui.compose.modifier.ParentDataModifier

@Composable
fun Box(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit = @Composable {}
) {
    Layout(
        content = { BoxScopeInstance.content() },
        modifiers = modifier,
        measurePolicy = BoxMeasurePolicy(alignment)
    )
}

@Immutable
interface BoxScope {
    @Stable
    fun Modifier.align(alignment: Alignment): Modifier
}

private class AlignModifier(
    private val alignment: Alignment
): ParentDataModifier {
    override fun modifyParentData(parentData: Any?): Any {
        return ((parentData as? BoxParentData) ?: BoxParentData()).also {
            it.alignment = alignment
        }
    }
}

private data class BoxParentData(
    var alignment: Alignment = Alignment.Center
)

private val Measurable.boxParentData: BoxParentData?
    get() = this.parentData as? BoxParentData

private object BoxScopeInstance: BoxScope {
    override fun Modifier.align(alignment: Alignment): Modifier =
        this then AlignModifier(alignment)
}

internal class BoxMeasurePolicy(
    private val contentAlignment: Alignment = Alignment.Center
): MeasurePolicy {
    override fun MeasureScope.measure(constraints: Constraints, measurables: List<Measurable>): MeasureResult {
        var width = 0
        var height = 0

        val placeables = measurables.map { measurable ->
            measurable.measure(constraints).also {
                width = maxOf(width, it.width)
                height = maxOf(height, it.height)
            }
        }

        width = constraints.constrainWidth(width)
        height = constraints.constrainHeight(height)

        return layout(width, height) {
            placeables.forEachIndexed { index, placeable ->
                val alignment = measurables[index].boxParentData?.alignment ?: contentAlignment
                val offset = alignment.align(IntSize(placeable.width, placeable.height), IntSize(width, height))
                placeable.place(offset.x, offset.y)
            }
        }
    }
}