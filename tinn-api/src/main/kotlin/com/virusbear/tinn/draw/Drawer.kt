package com.virusbear.tinn.draw

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.Destroyable
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Radians
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.vec
import javax.sound.sampled.Line

interface Drawer: Destroyable {
    fun begin(width: Int, height: Int, contentScale: Double)
    fun end()

    fun push()
    fun pop()

    fun clear(color: Color)

    var fill: Color?
    fun noFill()
    var stroke: Color?
    fun noStroke()
    var strokeWeight: Double

    fun translate(position: Vec2)
    fun rotate(angle: Radians)
    fun scale(scale: Vec2)

    fun image(image: ColorBuffer, position: Vec2 = Vec2.ZERO, offset: Vec2 = Vec2.ZERO, size: Vec2 = IVec2(image.width, image.height).vec)
    fun path(block: PathScope.() -> Unit)

    fun point(pos: Vec2)

    var lineCap: LineCap
    var lineJoin: LineJoin
    fun line(start: Vec2, end: Vec2)
    fun circle(center: Vec2, radius: Double)
    fun rectangle(corner: Vec2, size: Vec2)

    var font: Font?
    var fontSize: Double
    var vertivalTextAlign: VerticalTextAlign
    var horizontalTextAlign: HorizontalTextAlign
    fun text(pos: Vec2, text: String)

    fun measureText(text: String): Vec2

    fun loadFont(name: String, path: String): Font
}