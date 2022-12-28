package com.virusbear.tinn.opengl

import com.virusbear.tinn.BufferWriter
import com.virusbear.tinn.ColorFormat
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import java.nio.ByteBuffer

open class BufferWriterGL(
    private val buffer: ByteBuffer
): BufferWriter {

    override fun write(v: Byte) {
        buffer.put(v)
    }

    override fun write(v: Short) {
        buffer.putShort(v)
    }

    override fun write(v: Int) {
        buffer.putInt(v)
    }

    override fun write(v: Long) {
        buffer.putLong(v)
    }

    override fun write(v: Float) {
        buffer.putFloat(v)
    }

    override fun write(v: Double) {
        buffer.putDouble(v)
    }

    override fun write(v: IVec2) {
        write(v.x)
        write(v.y)
    }

    override fun write(v: IVec3) {
        write(v.x)
        write(v.y)
        write(v.z)
    }

    override fun write(v: IVec4) {
        write(v.x)
        write(v.y)
        write(v.z)
        write(v.w)
    }

    override fun write(v: Vec2) {
        write(v.y.toFloat())
        write(v.x.toFloat())
    }

    override fun write(v: Vec3) {
        write(v.y.toFloat())
        write(v.z.toFloat())
        write(v.x.toFloat())
    }

    override fun write(v: Vec4) {
        write(v.y.toFloat())
        write(v.z.toFloat())
        write(v.x.toFloat())
        write(v.w.toFloat())
    }

    override fun write(v: Mat3) {
        write(Vec3(v.c0r0, v.c0r1, v.c0r2))
        write(Vec3(v.c1r0, v.c1r1, v.c1r2))
        write(Vec3(v.c2r0, v.c2r1, v.c2r2))
    }
    override fun write(v: Mat4) {
        write(Vec4(v.c0r0, v.c0r1, v.c0r2, v.c0r3))
        write(Vec4(v.c1r0, v.c1r1, v.c1r2, v.c1r3))
        write(Vec4(v.c2r0, v.c2r1, v.c2r2, v.c2r3))
        write(Vec4(v.c3r0, v.c3r1, v.c3r2, v.c3r3))
    }
    override fun write(v: Color, format: ColorFormat) {
        if(format.isFloatingPoint) {
            if(format.type.bytes != 4) {
                //TODO: fix this
                error("non 32bit floats are currently not supported")
            }

            write(Vec4(v.r, v.g, v.b, v.a))
        } else {
            fun writeComponentValue(value: Double) {
                val maxValue = (1L shl (format.type.bytes * 8)) - 1
                val result = (value.coerceIn(0.0, 1.0) * maxValue).toLong() and maxValue
                when(format.type.bytes) {
                    1 -> write((result and 0xFF).toByte())
                    2 -> write((result and 0xFFFF).toShort())
                    4 -> write((result and 0xFFFFFFFF).toInt())
                    8 -> write(result)
                }
            }

            if(format.channels >= 1) {
                writeComponentValue(v.r)
            }
            if(format.channels >= 2) {
                writeComponentValue(v.g)
            }
            if(format.channels >= 3) {
                writeComponentValue(v.b)
            }
            if(format.channels == 4) {
                writeComponentValue(v.a)
            }
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