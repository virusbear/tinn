package com.virusbear.tinn.ui.compose

fun interface MeasurePolicy {
    fun MeasureScope.measure(constraints: Constraints, measurables: List<Measurable>): MeasureResult
}