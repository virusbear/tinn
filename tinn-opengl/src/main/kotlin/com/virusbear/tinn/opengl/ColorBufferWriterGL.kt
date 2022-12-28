package com.virusbear.tinn.opengl

import com.virusbear.tinn.ColorBufferWriter
import com.virusbear.tinn.ColorFormat
import com.virusbear.tinn.color.Color
import java.nio.ByteBuffer

class ColorBufferWriterGL(
    private val width: Int,
    private val height: Int,
    private val format: ColorFormat,
    buffer: ByteBuffer
): ColorBufferWriter, BufferWriterGL(buffer) {
    override fun set(x: Int, y: Int, color: Color) {
        val position = this.position
        this.position = format.pixelBytes * (y * width + x)
        write(color, format)
        this.position = position
    }
}