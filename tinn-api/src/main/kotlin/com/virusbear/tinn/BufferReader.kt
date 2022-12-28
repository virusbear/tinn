package com.virusbear.tinn

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*

interface BufferReader {
    val byte: Byte
    val short: Short
    val int: Int
    val long: Long
    val float: Float
    val double: Double
    val iVec2: IVec2
    val iVec3: IVec3
    val iVec4: IVec4
    val vec2: Vec2
    val vec3: Vec3
    val vec4: Vec4
    val mat3: Mat3
    val mat4: Mat4
    fun color(format: ColorFormat): Color

    var position: Int
    val limit: Int
    val capacity: Int
    val remaining: Int
}