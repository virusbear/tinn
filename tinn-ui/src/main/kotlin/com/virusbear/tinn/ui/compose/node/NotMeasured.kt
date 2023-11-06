package com.virusbear.tinn.ui.compose.node

import com.virusbear.tinn.ui.compose.MeasureResult

internal object NotMeasured: MeasureResult {
    override val width: Int = 0
    override val height: Int = 0

    override fun placeChildren() = throw UnsupportedOperationException("Not measured")
}