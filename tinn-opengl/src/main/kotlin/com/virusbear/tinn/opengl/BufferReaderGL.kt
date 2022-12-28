package com.virusbear.tinn.opengl

import com.virusbear.tinn.BufferReader
import com.virusbear.tinn.ColorFormat
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import java.nio.ByteBuffer

open class BufferReaderGL(
    private val buffer: ByteBuffer
): BufferReader {
    override val byte: Byte
        get() = buffer.get()
    override val short: Short
        get() = buffer.short
    override val int: Int
        get() = buffer.int
    override val long: Long
        get() = buffer.long
    override val float: Float
        get() = buffer.float
    override val double: Double
        get() = buffer.double
    override val iVec2: IVec2
        get() = IVec2(
            int,
            int
        )
    override val iVec3: IVec3
        get() = IVec3(
            int,
            int,
            int
        )

    override val iVec4: IVec4
        get() = IVec4(
            int,
            int,
            int,
            int
        )
    override val vec2: Vec2
        get() = Vec2(
            float.toDouble(),
            float.toDouble()
        )
    override val vec3: Vec3
        get() = Vec3(
            float.toDouble(),
            float.toDouble(),
            float.toDouble()
        )
    override val vec4: Vec4
        get() = Vec4(
            float.toDouble(),
            float.toDouble(),
            float.toDouble(),
            float.toDouble()
        )
    override val mat3: Mat3
        get() {
            val c1 = vec3
            val c2 = vec3
            val c3 = vec3

            return Mat3(
                c1.x, c2.x, c3.x,
                c1.y, c2.y, c3.y,
                c1.z, c2.z, c3.z
            )
        }
    override val mat4: Mat4
        get() {
            val c1 = vec4
            val c2 = vec4
            val c3 = vec4
            val c4 = vec4

            return Mat4(
                c1.x, c2.x, c3.x, c4.x,
                c1.y, c2.y, c3.y, c4.y,
                c1.z, c2.z, c3.z, c4.z,
                c1.w, c2.w, c3.w, c4.w
            )
        }

    override fun color(format: ColorFormat): Color {
        if(format.isFloatingPoint) {
            if(format.type.bytes != 4) {
                //TODO: fix this
                error("non 32bit floats are currently not supported")
            }

            val color = vec4
            return Color(color.x, color.y, color.z, color.w)
        } else {
            fun readComponentValue(): Double {
                val maxValue = (1L shl (format.type.bytes * 8)) -1
                val read = when(format.type.bytes) {
                    1 -> byte.toUByte().toLong()
                    2 -> short.toUShort().toLong()
                    4 -> int.toUInt().toLong()
                    8 -> long
                    else -> error("Unsupported component size: ${format.type.bytes}")
                }

                return (read.toDouble() / maxValue.toDouble()).coerceIn(0.0, 1.0)
            }

            val r = if(format.channels >= 1) {
                readComponentValue()
            } else { 0.0 }
            val g = if(format.channels >= 1) {
                readComponentValue()
            } else { 0.0 }
            val b = if(format.channels >= 1) {
                readComponentValue()
            } else { 0.0 }
            val a = if(format.channels >= 1) {
                readComponentValue()
            } else { 1.0 }

            return Color(r, g, b, a)
        }
    }

    override var position: Int
        get() = buffer.position()
        set(value) {
            buffer.position(value)
        }

    override val limit: Int
        get() = buffer.limit()
    override val capacity: Int
        get() = buffer.capacity()
    override val remaining: Int
        get() = buffer.remaining()
}