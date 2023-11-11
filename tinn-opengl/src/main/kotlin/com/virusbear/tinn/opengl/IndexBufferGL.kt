package com.virusbear.tinn.opengl

import com.virusbear.tinn.BufferProxy
import com.virusbear.tinn.Driver
import com.virusbear.tinn.IndexBuffer
import com.virusbear.tinn.Trackable
import org.lwjgl.opengl.GL15C.*

class IndexBufferGL(
    override val size: Int,
    private val context: ContextGL,
    driver: Driver
): IndexBuffer, Trackable(driver) {
    private val ebo: Int

    override val proxy: BufferProxy
        get() = TODO("Not yet implemented")

    init {
        ebo = context.glGenBuffers()
        context.checkGLErrors()

        bound {
            context.glBufferData(GL_ELEMENT_ARRAY_BUFFER, size * 4L, GL_DYNAMIC_DRAW)
        }
    }

    override fun bind() {
        require(!destroyed) { "IndexBuffer is destroyed" }

        context.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo)
        context.checkGLErrors()
    }

    override fun unbind() {
        context.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
        context.checkGLErrors()
    }

    override fun destroy() {
        if(destroyed)
            return

        context.glDeleteBuffers(ebo)
        context.checkGLErrors()

        super.destroy()
    }
}