package com.virusbear.tinn.ui.compose.node

import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.extensions.isolated
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.vec
import com.virusbear.tinn.ui.compose.modifier.AbstractDrawScope
import com.virusbear.tinn.ui.compose.modifier.ContentDrawScope
import com.virusbear.tinn.ui.compose.modifier.DrawModifier

internal class DrawLayer(
    private val element: DrawModifier,
    private val lowerLayer: TinnNodeLayer
): AbstractTinnNodeLayer(lowerLayer) {
    override fun draw(drawer: Drawer) {
        drawer.isolated {
            translate(IVec2(x, y).vec)

            val scope = object: AbstractDrawScope(this, width, height), ContentDrawScope {
                override fun drawContent() {
                    lowerLayer.draw(this)
                }
            }

            element.run { scope.draw() }
            lowerLayer.draw(drawer)
        }
    }
}