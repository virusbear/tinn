package com.virusbear.tinn.ui.compose

import com.virusbear.tinn.ui.compose.androidx.Constraints

interface Measurable: IntrinsicMeasurable {
    fun measure(constraints: Constraints): Placeable
}