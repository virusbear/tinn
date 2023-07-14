package com.virusbear.tinn.opengl

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.RenderTarget
import com.virusbear.tinn.Trackable
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.draw.*
import com.virusbear.tinn.math.Radians
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.vec
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NVGPaint
import org.lwjgl.nanovg.NVGTextRow
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.nanovg.NanoVG.*
import java.util.*

class NanoVGDrawer: Drawer, Trackable() {
    private val ctx = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS or NanoVGGL3.NVG_STENCIL_STROKES or NanoVGGL3.NVG_IMAGE_NODELETE)
    private val imageHandles = mutableMapOf<ColorBuffer, Int>()

    data class DrawerState(
        val fill: Color?,
        val stroke: Color?,
        val strokeWeight: Double
    )

    private val states = Stack<DrawerState>()

    override fun begin(width: Int, height: Int, contentScale: Double) {
        nvgBeginFrame(ctx, width.toFloat(), height.toFloat(),  contentScale.toFloat())
    }

    override fun end() {
        nvgEndFrame(ctx)
    }

    override fun push() {
        nvgSave(ctx)
        nvgReset(ctx)

        states.push(DrawerState(fill, stroke, strokeWeight))
    }

    override fun pop() {
        val state = states.pop()
        fill = state.fill
        stroke = state.stroke
        strokeWeight = state.strokeWeight

        nvgRestore(ctx)
    }

    override fun clear(color: Color) {
        nvgBeginPath(ctx)
        nvgRect(ctx, 0f, 0f, RenderTarget.active.width.toFloat(), RenderTarget.active.height.toFloat())
        val fill = NVGColor.create().apply {
            r(color.r.toFloat())
            g(color.g.toFloat())
            b(color.b.toFloat())
            a(color.a.toFloat())
        }
        nvgFillColor(ctx, fill)
        nvgFill(ctx)
    }

    override var fill: Color? = null

    override fun noFill() {
        fill = null
    }

    override var stroke: Color? = null

    override fun noStroke() {
        stroke = null
    }

    override var strokeWeight: Double = 1.0

    override fun translate(position: Vec2) {
        nvgTranslate(ctx, position.x.toFloat(), position.y.toFloat())
    }

    override fun rotate(angle: Radians) {
        nvgRotate(ctx, angle.value.toFloat())
    }

    override fun scale(scale: Vec2) {
        nvgScale(ctx, scale.x.toFloat(), scale.y.toFloat())
    }

    override fun image(image: ColorBuffer, position: Vec2, offset: Vec2, size: Vec2) {
        val paint = NVGPaint.create()

        val handle = imageHandles.computeIfAbsent(image) {
            imageHandles.filterKeys { it.destroyed }.forEach { (img, _) ->
                imageHandles -= img
            }
            NanoVGGL3.nvglCreateImageFromHandle(ctx, (image as ColorBufferGL).textureId, image.width, image.height, 0)
        }

        val maskPosition = position - offset
        val maskSize = image.size.vec

        nvgImagePattern(ctx, maskPosition.x.toFloat(), maskPosition.y.toFloat(), maskSize.x.toFloat(), maskSize.y.toFloat(), 0f, handle, 1f, paint)

        nvgBeginPath(ctx)
        nvgRect(ctx, position.x.toFloat(), position.y.toFloat(), size.x.toFloat(), size.y.toFloat())
        nvgFillPaint(ctx, paint)
        nvgFill(ctx)
        nvgClosePath(ctx)
    }

    override fun path(block: PathScope.() -> Unit) {
        nvgBeginPath(ctx)
        NanoVGPathScope().block()
        nvgClosePath(ctx)
    }

    override fun point(pos: Vec2) {
        if(fill == null) {
            return
        }
        path {
            circle(pos, 1.0)
            fill()
        }
    }

    override var lineCap: LineCap = LineCap.Butt
    override var lineJoin: LineJoin = LineJoin.None

    override fun line(start: Vec2, end: Vec2) {
        if(stroke == null) {
            return
        }

        path {
            moveTo(start)
            lineTo(end)
            stroke()
        }
    }

    override fun circle(center: Vec2, radius: Double) {
        if(stroke == null && fill == null) {
            return
        }

        path {
            circle(center, radius)
            stroke()
            fill()
        }
    }

    override fun rectangle(corner: Vec2, size: Vec2) {
        if(stroke == null && fill == null) {
            return
        }

        path {
            rect(corner, size)
            stroke()
            fill()
        }
    }

    override fun draw(drawable: Drawable) {
        draw((drawable as DrawableGL).drawable)
    }

    override var font: Font? = null
    override var fontSize: Double = 14.0
    override var vertivalTextAlign: VerticalTextAlign = VerticalTextAlign.Middle
    override var horizontalTextAlign: HorizontalTextAlign = HorizontalTextAlign.Left

    override fun text(pos: Vec2, text: String) {
        if(font == null) {
            return
        }

        setupFont()
        nvgText(ctx, pos.x.toFloat(), pos.y.toFloat(), text)
    }

    override fun measureText(text: String): Vec2 {
        if(font == null) {
            return Vec2.ZERO
        }

        setupFont()
        val lineHeight = FloatArray(1)
        nvgTextMetrics(ctx, null, null, lineHeight)
        val width = nvgTextBounds(ctx, 0f, 0f, text, null as FloatArray?)

        return Vec2(width.toDouble(), lineHeight[0].toDouble())
    }

    private fun setupFont() {
        nvgTextAlign(ctx, nvgTextAlignInt(vertivalTextAlign, horizontalTextAlign))
        nvgFontFace(ctx, font!!.name)
        nvgFontSize(ctx, fontSize.toFloat())
    }

    override fun loadFont(name: String, path: String): Font =
        NanoVGFont(name, nvgCreateFont(ctx, name, path))

    override fun destroy() {
        NanoVGGL3.nvgDelete(ctx)

        super.destroy()
    }

    inner class NanoVGPathScope: PathScope {
        override fun moveTo(pos: Vec2) {
            nvgMoveTo(ctx, pos.x.toFloat(), pos.y.toFloat())
        }

        override fun lineTo(pos: Vec2) {
            nvgLineCap(ctx, lineCap.nvg)
            if(lineJoin != LineJoin.None) {
                nvgLineJoin(ctx, lineJoin.nvg)
            }

            nvgLineTo(ctx, pos.x.toFloat(), pos.y.toFloat())
        }

        override fun bezierTo(control1: Vec2, control2: Vec2, anchor: Vec2) {
            nvgBezierTo(ctx,
                control1.x.toFloat(), control1.y.toFloat(),
                control2.x.toFloat(), control2.y.toFloat(),
                anchor.x.toFloat(), anchor.y.toFloat()
            )
        }

        override fun quadraticTo(control: Vec2, anchor: Vec2) {
            nvgQuadTo(ctx,
                control.x.toFloat(), control.y.toFloat(),
                anchor.x.toFloat(), anchor.y.toFloat()
            )
        }

        override fun arcTo(a: Vec2, b: Vec2, radius: Double) {
            nvgArcTo(ctx,
                a.x.toFloat(), a.y.toFloat(),
                b.x.toFloat(), b.y.toFloat(),
                radius.toFloat()
            )
        }

        override fun arc(center: Vec2, radius: Double, startAngle: Radians, endAngle: Radians, direction: PathWinding) {
            nvgArc(ctx,
                center.x.toFloat(), center.y.toFloat(),
                radius.toFloat(),
                startAngle.value.toFloat(),
                endAngle.value.toFloat(),
                when(direction) {
                    PathWinding.CounterClockwise -> NVG_CCW
                    else -> NVG_CW
                }
            )
        }

        override fun rect(corner: Vec2, size: Vec2) {
            nvgRect(ctx,
                corner.x.toFloat(), corner.y.toFloat(),
                size.x.toFloat(), size.y.toFloat()
            )
        }

        override fun roundedRect(corner: Vec2, size: Vec2, radius: Double) {
            nvgRoundedRect(ctx,
                corner.x.toFloat(), corner.y.toFloat(),
                size.x.toFloat(), size.y.toFloat(),
                radius.toFloat()
            )
        }

        override fun roundedRect(
            corner: Vec2,
            size: Vec2,
            radiusTopLeft: Double,
            radiusTopRight: Double,
            radiusBottomRight: Double,
            radiusBottomLeft: Double
        ) {
            nvgRoundedRectVarying(ctx,
                corner.x.toFloat(), corner.y.toFloat(),
                size.x.toFloat(), size.y.toFloat(),
                radiusTopLeft.toFloat(),
                radiusTopRight.toFloat(),
                radiusBottomRight.toFloat(),
                radiusBottomLeft.toFloat()
            )
        }

        override fun circle(center: Vec2, radius: Double) {
            nvgCircle(ctx,
                center.x.toFloat(), center.y.toFloat(),
                radius.toFloat()
            )
        }

        override fun ellipse(center: Vec2, radii: Vec2) {
            nvgEllipse(ctx,
                center.x.toFloat(), center.y.toFloat(),
                radii.x.toFloat(), radii.y.toFloat()
            )
        }

        override fun fill() {
            fill?.let { value ->
                nvgFillColor(ctx, NVGColor.create().apply {
                    r(value.r.toFloat())
                    g(value.g.toFloat())
                    b(value.b.toFloat())
                    a(value.a.toFloat())
                })
                nvgFill(ctx)
            }
        }

        override fun stroke() {
            stroke?.let { value ->
                nvgStrokeWidth(ctx, strokeWeight.toFloat())
                nvgStrokeColor(ctx, NVGColor.create().apply {
                    r(value.r.toFloat())
                    g(value.g.toFloat())
                    b(value.b.toFloat())
                    a(value.a.toFloat())
                })
                nvgStroke(ctx)
            }
        }
    }
}

data class NanoVGFont(
    override val name: String,
    val font: Int
): Font