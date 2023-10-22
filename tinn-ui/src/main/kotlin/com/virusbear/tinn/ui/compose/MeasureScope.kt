package com.virusbear.tinn.ui.compose

import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer

interface MeasureScope {
    fun layout(
        width: Int,
        height: Int,
        block: PlacementScope.() -> Unit
    ): MeasureResult =
        LayoutResult(this as PlacementScope, width, height, block)
}

private class LayoutResult(
    private val placementScope: PlacementScope,
    override val width: Int,
    override val height: Int,
    private val block: PlacementScope.() -> Unit
): MeasureResult {
    override fun placeChildren() =
        placementScope.block()
}

interface MeasureResult {
    val width: Int
    val height: Int

    fun placeChildren()
}

interface LayoutModifier: Modifier.Element {
    fun MeasureScope.measure(measurable: Measurable): MeasureResult
}

fun Modifier.layout(
    //TODO: add constraints to measuring process
    measure: MeasureScope.(Measurable) -> MeasureResult
) = this then LayoutModifierElement(measure)

private class LayoutModifierElement(
    val block: MeasureScope.(Measurable) -> MeasureResult
): LayoutModifier {
    override fun MeasureScope.measure(measurable: Measurable): MeasureResult =
        block(measurable)
}

@Composable fun Drawable(width: Int, height: Int, drawable: Drawer.() -> Unit) {
    ComposeNode<DrawableNode, Applier<Any>>(
        factory = ::DrawableNode
    ) {
        set(width) { this.width = it }
        set(height) { this.height = it }
        set(drawable) { this.drawable = it }
    }
}