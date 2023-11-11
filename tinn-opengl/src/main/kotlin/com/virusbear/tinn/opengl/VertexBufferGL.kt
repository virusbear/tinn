package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import org.lwjgl.opengl.GL30C.*

class VertexBufferGL internal constructor(
    override val size: Int,
    override val format: VertexFormat,
    private val context: ContextGL,
    driver: Driver
): VertexBuffer, Trackable(driver) {
    private val vao: Int
    private val vbo: Int

    override val proxy: BufferProxy
        get() = TODO("Not yet implemented")

    init {
        vbo = context.glGenBuffers()
        context.checkGLErrors()
        vao = context.glGenVertexArrays()
        context.checkGLErrors()

        bound {
            context.glBindBuffer(GL_ARRAY_BUFFER, vbo)
            context.glBufferData(GL_ARRAY_BUFFER, size.toLong() * format.size, GL_DYNAMIC_DRAW)
            context.checkGLErrors()

            var offset = 0
            val stride = format.size

            format.attributes().forEachIndexed { idx, attribute ->
                context.glVertexAttribPointer(idx, attribute.components, attribute.type.gl, attribute.normalized, stride, offset.toLong())
                context.glEnableVertexAttribArray(idx)
                context.checkGLErrors()

                offset += if(format.interleaved) {
                    attribute.size
                } else {
                    attribute.size * size
                }
            }

            context.glBindBuffer(GL_ARRAY_BUFFER, 0)
        }
    }

    override fun bind() {
        require(!destroyed) { "VertexBuffer is destroyed" }

        context.glBindVertexArray(vao)
        context.checkGLErrors()
    }

    override fun unbind() {
        context.glBindVertexArray(0)
        context.checkGLErrors()
    }

    override fun destroy() {
        if(destroyed)
            return

        context.glDeleteVertexArrays(vao)
        context.glDeleteBuffers(vbo)

        context.checkGLErrors()

        super.destroy()
    }
}