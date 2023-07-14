package com.virusbear.tinn.draw

import com.virusbear.tinn.math.Radians
import com.virusbear.tinn.math.Vec2

interface PathScope {
    fun moveTo(pos: Vec2)
    fun lineTo(pos: Vec2)
    fun bezierTo(control1: Vec2, control2: Vec2, anchor: Vec2)
    fun quadraticTo(control: Vec2, anchor: Vec2)
    fun arcTo(start: Vec2, end: Vec2, radius: Double)
    fun arc(center: Vec2, radius: Double, startAngle: Radians, endAngle: Radians, direction: PathWinding = PathWinding.Clockwise)
    fun rect(corner: Vec2, size: Vec2)
    fun roundedRect(corner: Vec2, size: Vec2, radius: Double)
    fun roundedRect(corner: Vec2, size: Vec2, radiusTopLeft: Double, radiusTopRight: Double, radiusBottomRight: Double, radiusBottomLeft: Double)
    fun ellipse(center: Vec2, radii: Vec2)
    fun circle(center: Vec2, radius: Double) {
        ellipse(center, Vec2.ONE * radius)
    }
}