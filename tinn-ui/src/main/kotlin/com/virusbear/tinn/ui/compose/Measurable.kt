package com.virusbear.tinn.ui.compose

interface Measurable {
    fun measure(constraints: Constraints): Placeable
}