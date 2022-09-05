package com.virusbear.tinn.opengl

import com.virusbear.tinn.VertexBuffer
import com.virusbear.tinn.VertexFormat
import org.lwjgl.opengl.GL15C.*
import org.lwjgl.opengl.GL20C.glVertexAttribPointer

class VertexBufferGL private constructor(
    private val buffer: Int,
    override val size: Int,
    override val format: VertexFormat
): VertexBuffer {
    companion object {
        fun create(
            size: Int,
            format: VertexFormat
        ): VertexBuffer {
            val buffer = glGenBuffers()
            checkGLErrors()

            return VertexBufferGL(
                buffer,
                size,
                format
            ).apply {
                bound {
                    glBufferData(GL_ARRAY_BUFFER, size.toLong() * format.size, GL_DYNAMIC_DRAW)
                    checkGLErrors()

                    var offset = 0
                    val stride = if(format.interleaved) format.size else 0

                    format.attributes().forEachIndexed { idx, attribute ->
                        glVertexAttribPointer(idx, attribute.components, attribute.type.gl, attribute.normalized, stride, offset.toLong())
                        checkGLErrors()

                        offset += if(format.interleaved) {
                            attribute.size
                        } else {
                            attribute.size * size
                        }
                    }
                }
            }
        }
    }

    override fun bind() {
        require(!destroyed) { "VertexBuffer is destroyed" }

        glBindBuffer(GL_ARRAY_BUFFER, buffer)
        checkGLErrors()
    }

    override fun unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        checkGLErrors()
    }

    override var destroyed: Boolean = false
        private set

    override fun destroy() {
        glDeleteBuffers(buffer)
        checkGLErrors()
        destroyed = true
    }
}