package com.virusbear.tinn.opengl

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.ColorBufferProxy
import org.lwjgl.opengl.GL11C.glGetTexImage
import org.lwjgl.opengl.GL11C.glTexImage2D
import org.lwjgl.system.MemoryUtil
import java.nio.ByteBuffer

class ColorBufferProxyGL(private val colorBuffer: ColorBufferGL): ColorBufferProxy, BaseDestroyable() {
    private var buffer: ByteBuffer? = null

    override fun download() {
        if(destroyed) return
        if(buffer != null) {
            //TODO: logging
            MemoryUtil.memFree(buffer)
        }

        val bufferSize = colorBuffer.width * colorBuffer.height * colorBuffer.format.pixelBytes
        buffer = MemoryUtil.memAlloc(bufferSize)

        colorBuffer.bound {
            glGetTexImage(colorBuffer.textureId, 0, colorBuffer.format.glFormat, colorBuffer.format.glType, buffer!!)
        }
    }

    override fun upload() {
        if(destroyed) return
        if(buffer == null) {
            //TODO: logging
            return
        }

        colorBuffer.bound {
            glTexImage2D(colorBuffer.target, 0, colorBuffer.format.internalFormat, colorBuffer.width, colorBuffer.height, 0, colorBuffer.format.glFormat, colorBuffer.format.glType, buffer)
            MemoryUtil.memFree(buffer)
            buffer = null
        }
    }

    override fun writer(): ColorBufferWriterGL {
        if(buffer == null) {
            //TODO: logging -> download not yet called. no image available
            error("No Buffer")
        }

        return ColorBufferWriterGL(colorBuffer.width, colorBuffer.height, colorBuffer.format, buffer!!)
    }

    override fun reader(): ColorBufferReaderGL {
        if(buffer == null) {
            //TODO: logging -> download not yet called. no image available
            error("No Buffer")
        }

        return ColorBufferReaderGL(colorBuffer.width, colorBuffer.height, colorBuffer.format, buffer!!)
    }


    override fun destroy() {
        super.destroy()

        MemoryUtil.memFree(buffer)
    }

}