package com.virusbear.tinn

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*

interface BufferWriter {
    fun write(v: Byte)
    fun write(v: Short)
    fun write(v: Int)
    fun write(v: Long)
    fun write(v: Float)
    fun write(v: Double)
    fun write(v: IVec2)
    fun write(v: IVec3)
    fun write(v: Vec2)
    fun write(v: Vec3)
    fun write(v: Mat3)
    fun write(v: Mat4)
    fun write(v: Color)

    var position: Int
}