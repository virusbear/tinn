package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import org.lwjgl.opengl.GL33C.*
import java.nio.ByteBuffer

class ColorBufferGL internal constructor(
    override val width: Int,
    override val height: Int,
    private val format: ColorFormat,
    private val samples: MultiSample,
    private val levels: MipMapLevel
): ColorBuffer, Trackable() {
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

    override fun generateMipMaps() {
        require(samples == MultiSample.None) { "Multisampled mipmaps not supported" }
        if(levels == MipMapLevel.None)
            return

        bound {
            glGenerateMipmap(target)
        }
    }

    override fun filter(minifyingFilter: TextureFilter, magnifyingFilter: TextureFilter) {
        bound {
            glTexParameteri(target, GL_TEXTURE_MIN_FILTER, minifyingFilter.gl)
            glTexParameteri(target, GL_TEXTURE_MAG_FILTER, magnifyingFilter.gl)
        }
    }

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

        super.destroy()
    }
}