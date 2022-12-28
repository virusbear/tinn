package com.virusbear.tinn.opengl

import com.virusbear.tinn.ColorBufferReader
import com.virusbear.tinn.ColorFormat
import com.virusbear.tinn.color.Color
import java.nio.ByteBuffer

class ColorBufferReaderGL(
    private val width: Int,
    private val height: Int,
    private val format: ColorFormat,
    buffer: ByteBuffer
): ColorBufferReader, BufferReaderGL(buffer) {
    override fun get(x: Int, y: Int): Color {
        val position = this.position
        this.position = format.pixelBytes * (y * width + x)
        val result = color(format)
        this.position = position
        return result
    }

}