package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import org.lwjgl.opengl.GL15C.*

class IndexBufferGL(
    override val size: Int,
    override val context: Context
): IndexBuffer, ContextAwareDestroyable() {
    private val ebo: Int

    override val proxy: BufferProxy
        get() = TODO("Not yet implemented")

    init {
        ebo = context.execute { glGenBuffers().also { checkGLErrors() } }

        bound {
            context.execute { glBufferData(GL_ELEMENT_ARRAY_BUFFER, size * 4L, GL_DYNAMIC_DRAW) }
        }
    }

    override fun bind() {
        require(!destroyed) { "IndexBuffer is destroyed" }

        context.execute {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo)
            checkGLErrors()
        }
    }

    override fun unbind() {
        context.execute {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
            checkGLErrors()
        }
    }

    override fun destroy() {
        if(destroyed)
            return

        context.execute {
            glDeleteBuffers(ebo)
            checkGLErrors()
        }

        super.destroy()
    }
}