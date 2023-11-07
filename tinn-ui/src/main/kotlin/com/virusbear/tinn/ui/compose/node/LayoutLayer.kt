package com.virusbear.tinn.ui.compose.node

import com.virusbear.tinn.ui.compose.androidx.Constraints
import com.virusbear.tinn.ui.compose.MeasureResult
import com.virusbear.tinn.ui.compose.modifier.LayoutModifier

internal class LayoutLayer(
    private val element: LayoutModifier,
    private val lowerLayer: TinnNodeLayer
): AbstractTinnNodeLayer(lowerLayer) {
    override fun doMeasure(constraints: Constraints): MeasureResult {
        return element.run { measure(constraints, lowerLayer) }
    }
}