package com.virusbear.tinn.opengl

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.ColorFormat
import com.virusbear.tinn.MipMapLevel
import com.virusbear.tinn.MultiSample
import org.lwjgl.opengl.GL33C.*
import java.nio.ByteBuffer

class ColorBufferGL private constructor(
    private val target: Int,
    private val texture: Int,
    private val colorFormat: ColorFormat,
    private val samples: MultiSample,
    override val width: Int,
    override val height: Int
): ColorBuffer {
    companion object {
        fun create(
            width: Int,
            height: Int,
            format: ColorFormat,
            multisample: MultiSample,
            levels: MipMapLevel
        ): ColorBuffer {
            require(width < LimitsGL.MaxTextureSize)
            require(height < LimitsGL.MaxTextureSize)
            require(multisample.samples < LimitsGL.MaxSamples)

            val texture = glGenTextures()
            checkGLErrors()

            return ColorBufferGL(
                when(multisample) {
                    MultiSample.None -> GL_TEXTURE_2D
                    else -> GL_TEXTURE_2D_MULTISAMPLE
                },
                texture,
                format,
                multisample,
                width,
                height
            ).apply {
                bound {
                    if(levels != MipMapLevel.None) {
                        glTexParameteri(target, GL_TEXTURE_MAX_LEVEL, levels.levels - 1)
                    }

                    when(multisample) {
                        MultiSample.None -> glTexImage2D(target, 0, colorFormat.internalFormat, width, height, 0, colorFormat.glFormat, format.glType, null as ByteBuffer?)
                        else -> glTexImage2DMultisample(target, multisample.samples.coerceAtMost(LimitsGL.MaxSamples - 1), colorFormat.internalFormat, width, height, false)
                    }
                }
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