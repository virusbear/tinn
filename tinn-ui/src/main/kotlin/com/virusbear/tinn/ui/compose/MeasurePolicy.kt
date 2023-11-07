package com.virusbear.tinn.ui.compose

import com.virusbear.tinn.ui.compose.androidx.Constraints

fun interface MeasurePolicy {
    fun MeasureScope.measure(constraints: Constraints, measurables: List<Measurable>): MeasureResult
}