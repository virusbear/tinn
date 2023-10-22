package com.virusbear.tinn.ui.widgets

import com.virusbear.tinn.RenderTarget
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.extensions.colorBuffer
import com.virusbear.tinn.extensions.draw
import com.virusbear.tinn.extensions.renderTarget
import com.virusbear.tinn.math.Vec2
import kotlin.contracts.contract
import kotlin.math.ceil

class FrameBufferWidget(
    private val content: Widget
): Widget() {
    private var rt = createRenderTarget(content.size)

    init {
        attach(content)
    }

    override val size: Vec2 = content.size

    override fun measure(constraints: Constraints): Vec2 =
        constraints.maxSize

    override fun draw(drawer: Drawer, bounds: Vec2) {
        if(dirty) {
            rt.draw {
                clear(Color.BLACK)
                content.draw(drawer, bounds)
            }
        }

        drawer.image(rt.colorBuffer(0))

        super.draw(drawer, bounds)
    }

    private fun createRenderTarget(size: Vec2): RenderTarget =
        renderTarget(ceil(size.x).toInt(), ceil(size.y).toInt()) {
            colorBuffer()
        }
}