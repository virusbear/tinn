package com.virusbear.tinn.opengl

import com.virusbear.tinn.VertexBuffer
import com.virusbear.tinn.VertexFormat
import org.lwjgl.opengl.GL30C.*

class VertexBufferGL internal constructor(
    override val size: Int,
    override val format: VertexFormat
): VertexBuffer {
    private val vao: Int
    private val vbo: Int

    init {
        vbo = glGenBuffers()
        checkGLErrors()
        vao = glGenVertexArrays()
        checkGLErrors()

        bound {
            glBindBuffer(GL_ARRAY_BUFFER, vbo)
            glBufferData(GL_ARRAY_BUFFER, size.toLong() * format.size, GL_DYNAMIC_DRAW)
            checkGLErrors()

            var offset = 0
            val stride = format.size

            format.attributes().forEachIndexed { idx, attribute ->
                glVertexAttribPointer(idx, attribute.components, attribute.type.gl, attribute.normalized, stride, offset.toLong())
                glEnableVertexAttribArray(idx)
                checkGLErrors()

                offset += if(format.interleaved) {
                    attribute.size
                } else {
                    attribute.size * size
                }
            }

            glBindBuffer(GL_ARRAY_BUFFER, 0)
        }
    }

    override fun bind() {
        require(!destroyed) { "VertexBuffer is destroyed" }

        glBindVertexArray(vao)
        checkGLErrors()
    }

    override fun unbind() {
        glBindVertexArray(0)
        checkGLErrors()
    }

    override var destroyed: Boolean = false
        private set

    override fun destroy() {
        glDeleteVertexArrays(vao)
        glDeleteBuffers(vbo)
        checkGLErrors()
        destroyed = true
    }
}