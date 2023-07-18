package com.virusbear.tinn.ui.widgets

import com.virusbear.tinn.RenderTarget
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.extensions.colorBuffer
import com.virusbear.tinn.extensions.draw
import com.virusbear.tinn.extensions.renderTarget
import com.virusbear.tinn.math.Vec2
import kotlin.math.ceil

class FrameBufferWidget(
    private val content: Widget
): Widget() {
    private var rt = createRenderTarget(content.size)

    init {
        attach(content)
    }

    override val size: Vec2 = content.size

    override fun draw(drawer: Drawer) {
        if(dirty) {
            rt.draw {
                clear(Color.BLACK)
                content.draw(this)
            }
        }

        drawer.image(rt.colorBuffer(0))

        super.draw(drawer)
    }

    private fun createRenderTarget(size: Vec2): RenderTarget =
        renderTarget(ceil(size.x).toInt(), ceil(size.y).toInt()) {
            colorBuffer()
        }
}