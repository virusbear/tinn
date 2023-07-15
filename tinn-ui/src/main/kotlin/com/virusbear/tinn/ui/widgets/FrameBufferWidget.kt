package com.virusbear.tinn.ui.widgets

import com.virusbear.tinn.RenderTarget
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.draw.Drawer
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

    override var size: Vec2
        get() = content.size
        set(value) {
            if(value == content.size) {
                return
            }
            rt.destroy()
            rt = createRenderTarget(value)
            content.size = value
            dirty = true
        }

    override fun draw(drawer: Drawer) {
        //TODO: somehow rt.draw das not draw to any rendertarget, leaving rt.colorBuffer(0) completely empty
        //if(dirty) {
            rt.draw {
                drawer.clear(Color.BLACK)
                content.draw(drawer)
            }
        //}

        drawer.image(rt.colorBuffer(0))

        super.draw(drawer)
    }

    private fun createRenderTarget(size: Vec2): RenderTarget =
        renderTarget(ceil(size.x).toInt(), ceil(size.y).toInt()) {
            colorBuffer()
        }
}