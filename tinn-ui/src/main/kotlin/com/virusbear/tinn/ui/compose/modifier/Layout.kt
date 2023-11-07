package com.virusbear.tinn.ui.compose.modifier

import com.virusbear.tinn.ui.compose.*
import com.virusbear.tinn.ui.compose.androidx.Constraints

interface LayoutModifier: Modifier.Element {
    fun MeasureScope.measure(constraints: Constraints, measurable: Measurable): MeasureResult
}

fun Modifier.layout(
    measure: MeasureScope.(Constraints, Measurable) -> MeasureResult
) = this then LayoutModifierElement(measure)

private class LayoutModifierElement(
    val block: MeasureScope.(Constraints, Measurable) -> MeasureResult
): LayoutModifier {
    override fun MeasureScope.measure(constraints: Constraints, measurable: Measurable): MeasureResult =
        block(constraints, measurable)
}