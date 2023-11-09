package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import org.lwjgl.opengl.GL30C.*

class VertexBufferGL internal constructor(
    override val size: Int,
    override val format: VertexFormat,
    override val context: Context
): VertexBuffer, ContextAwareDestroyable() {
    private val vao: Int
    private val vbo: Int

    override val proxy: BufferProxy
        get() = TODO("Not yet implemented")

    init {
        vbo = context.execute {
            glGenBuffers().also { checkGLErrors() }
        }
        vao = context.execute {
            glGenVertexArrays().also { checkGLErrors() }
        }


        bound {
            context.execute {
                glBindBuffer(GL_ARRAY_BUFFER, vbo)
                glBufferData(GL_ARRAY_BUFFER, size.toLong() * format.size, GL_DYNAMIC_DRAW)
                checkGLErrors()
            }

            var offset = 0
            val stride = format.size

            format.attributes().forEachIndexed { idx, attribute ->
                context.execute {
                    glVertexAttribPointer(idx, attribute.components, attribute.type.gl, attribute.normalized, stride, offset.toLong())
                    glEnableVertexAttribArray(idx)
                    checkGLErrors()
                }

                offset += if(format.interleaved) {
                    attribute.size
                } else {
                    attribute.size * size
                }
            }

            context.execute {
                glBindBuffer(GL_ARRAY_BUFFER, 0)
            }
        }
    }

    override fun bind() {
        require(!destroyed) { "VertexBuffer is destroyed" }

        context.execute {
            glBindVertexArray(vao)
            checkGLErrors()
        }
    }

    override fun unbind() {
        context.execute {
            glBindVertexArray(0)
            checkGLErrors()
        }
    }

    override fun destroy() {
        if(destroyed)
            return

        context.execute {
            glDeleteVertexArrays(vao)
            glDeleteBuffers(vbo)

            checkGLErrors()
        }

        super.destroy()
    }
}