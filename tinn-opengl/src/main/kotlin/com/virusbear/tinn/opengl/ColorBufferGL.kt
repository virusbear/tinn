package com.virusbear.tinn.opengl

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.ColorFormat
import com.virusbear.tinn.MipMapLevel
import com.virusbear.tinn.MultiSample
import org.lwjgl.opengl.GL33C.*
import java.nio.ByteBuffer

class ColorBufferGL internal constructor(
    override val width: Int,
    override val height: Int,
    private val format: ColorFormat,
    samples: MultiSample,
    levels: MipMapLevel
): ColorBuffer {
    private val target: Int
    private val texture: Int

    init {
        require(width < LimitsGL.MaxTextureSize)
        require(height < LimitsGL.MaxTextureSize)

        texture = glGenTextures()
        checkGLErrors()

        target = when(samples) {
            MultiSample.None -> GL_TEXTURE_2D
            else -> GL_TEXTURE_2D_MULTISAMPLE
        }

        bound {
            if(levels != MipMapLevel.None) {
                glTexParameteri(target, GL_TEXTURE_MAX_LEVEL, levels.levels - 1)
            }

            when(samples) {
                MultiSample.None -> glTexImage2D(target, 0, format.internalFormat, width, height, 0, format.glFormat, format.glType, null as ByteBuffer?)
                else -> glTexImage2DMultisample(target, samples.samples.coerceAtMost(LimitsGL.MaxSamples - 1), format.internalFormat, width, height, false)
            }
        }
    }

    override var destroyed: Boolean = false
    private set

    override fun bind() {
        require(!destroyed) { "ColorBuffer is destroyed" }

        glActiveTexture(GL_TEXTURE0)
        glBindTexture(target, texture)
        checkGLErrors()
    }

    override fun unbind() {
        glBindTexture(target, 0)
        checkGLErrors()
    }

    override fun destroy() {
        if(destroyed)
            return

        glDeleteTextures(texture)
        checkGLErrors()
        destroyed = true
    }
}