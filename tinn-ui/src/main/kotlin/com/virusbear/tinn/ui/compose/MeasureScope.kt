package com.virusbear.tinn.ui.compose

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