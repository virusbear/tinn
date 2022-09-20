package com.virusbear.tinn.opengl

import com.virusbear.tinn.BufferWriter
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
        buffer.putInt(v.x)
        buffer.putInt(v.y)
    }

    override fun write(v: IVec3) {
        buffer.putInt(v.x)
        buffer.putInt(v.y)
        buffer.putInt(v.z)
    }

    override fun write(v: Vec2) {
        buffer.putFloat(v.x.toFloat())
        buffer.putFloat(v.y.toFloat())
    }

    override fun write(v: Vec3) {
        buffer.putFloat(v.x.toFloat())
        buffer.putFloat(v.y.toFloat())
        buffer.putFloat(v.z.toFloat())
    }

    override fun write(v: Mat3) {
        TODO("Not yet implemented")
    }
    override fun write(v: Mat4) {
        TODO("Not yet implemented")
    }
    override fun write(v: Color) {
        TODO("Not yet implemented")
    }

    override var position: Int
        get() = buffer.position()
        set(value) {
            buffer.position(value)
        }
}