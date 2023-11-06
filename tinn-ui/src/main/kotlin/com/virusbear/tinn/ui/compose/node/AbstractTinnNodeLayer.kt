package com.virusbear.tinn.ui.compose.node

import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.ui.compose.Constraints
import com.virusbear.tinn.ui.compose.MeasureResult
import com.virusbear.tinn.ui.compose.Placeable

internal abstract class AbstractTinnNodeLayer(
    private val next: TinnNodeLayer?
): TinnNodeLayer {
    private var measureResult: MeasureResult = NotMeasured

    final override val width: Int
        get() = measureResult.width
    final override val height: Int
        get() = measureResult.height

    override fun measure(constraints: Constraints): Placeable = apply {
        measureResult = doMeasure(constraints)
    }

    protected open fun doMeasure(constraints: Constraints): MeasureResult {
        val placeable = next!!.measure(constraints)
        return object: MeasureResult {
            override val width: Int = placeable.width
            override val height: Int = placeable.height

            override fun placeChildren() {
                placeable.placeAt(0, 0)
            }
        }
    }

    final override var x: Int = 0
        private set
    final override var y: Int = 0
        private set

    final override fun placeAt(x: Int, y: Int) {
        measureResult.placeChildren()
    }

    override fun draw(drawer: Drawer) {
        next?.draw(drawer)
    }
}