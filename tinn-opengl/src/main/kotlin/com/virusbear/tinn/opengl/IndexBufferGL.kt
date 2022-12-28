package com.virusbear.tinn.opengl

import com.virusbear.tinn.BufferProxy
import com.virusbear.tinn.IndexBuffer
import com.virusbear.tinn.Trackable
import org.lwjgl.opengl.GL15C.*

class IndexBufferGL(
    override val size: Int
): IndexBuffer, Trackable() {
    private val ebo: Int

    override val proxy: BufferProxy
        get() = TODO("Not yet implemented")

    init {
        ebo = glGenBuffers()
        checkGLErrors()

        bound {
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, size * 4L, GL_DYNAMIC_DRAW)
        }
    }

    override fun bind() {
        require(!destroyed) { "IndexBuffer is destroyed" }

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo)
        checkGLErrors()
    }

    override fun unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
        checkGLErrors()
    }

    override fun destroy() {
        if(destroyed)
            return

        glDeleteBuffers(ebo)
        checkGLErrors()

        super.destroy()
    }
}