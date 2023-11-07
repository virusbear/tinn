package com.virusbear.tinn.ui.compose.node

import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.ui.compose.androidx.Constraints
import com.virusbear.tinn.ui.compose.MeasureResult

internal class BottomLayer(
    private val node: TinnNode
): AbstractTinnNodeLayer(null) {
    override fun doMeasure(constraints: Constraints): MeasureResult {
        return node.measurePolicy.run { measure(constraints, node.children) }
    }

    override fun draw(drawer: Drawer) {
        for(child in node.children) {
            if(child.width != 0 && child.height != 0) {
                child.topLayer.draw(drawer)
            }
        }
    }
}