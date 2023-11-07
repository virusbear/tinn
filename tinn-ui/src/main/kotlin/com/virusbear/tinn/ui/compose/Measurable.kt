package com.virusbear.tinn.ui.compose

interface Measurable: IntrinsicMeasurable {
    fun measure(constraints: Constraints): Placeable
}