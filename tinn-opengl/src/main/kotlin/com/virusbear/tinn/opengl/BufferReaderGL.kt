package com.virusbear.tinn.opengl

import com.virusbear.tinn.BufferReader
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
    override val mat3: Mat3
        get() = TODO("Not yet implemented")
    override val mat4: Mat4
        get() = TODO("Not yet implemented")
    override val color: Color
        get() = TODO("Not yet implemented")

    override var position: Int
        get() = buffer.position()
        set(value) {
            buffer.position(value)
        }
}