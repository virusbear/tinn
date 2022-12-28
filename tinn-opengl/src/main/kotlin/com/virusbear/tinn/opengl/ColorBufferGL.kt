package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL33C.*
import org.lwjgl.stb.STBImage
import org.lwjgl.stb.STBImage.*
import org.lwjgl.system.MemoryStack
import java.io.File
import java.nio.ByteBuffer
import java.nio.FloatBuffer

class ColorBufferGL internal constructor(
    override val width: Int,
    override val height: Int,
    val format: ColorFormat,
    val samples: MultiSample,
    val levels: MipMapLevel
): ColorBuffer, Trackable() {
    private val target: Int
    override val textureId: Int

    init {
        require(width < LimitsGL.MaxTextureSize)
        require(height < LimitsGL.MaxTextureSize)

        textureId = glGenTextures()
        checkGLErrors()

        target = when(samples) {
            MultiSample.None -> GL_TEXTURE_2D
            else -> GL_TEXTURE_2D_MULTISAMPLE
        }

        bound {
            if(levels != MipMapLevel.None) {
                glTexParameteri(target, GL_TEXTURE_MAX_LEVEL, levels.levels - 1)
            }

            glPixelStorei(GL_UNPACK_ROW_LENGTH, 0);

            when(samples) {
                MultiSample.None -> glTexImage2D(target, 0, format.internalFormat, width, height, 0, format.glFormat, format.glType, null as ByteBuffer?)
                else -> glTexImage2DMultisample(target, samples.samples.coerceAtMost(LimitsGL.MaxSamples - 1), format.internalFormat, width, height, false)
            }
            checkGLErrors()
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
        glBindTexture(target, textureId)
        checkGLErrors()
    }

    override fun unbind() {
        glBindTexture(target, 0)
        checkGLErrors()
    }

    override fun destroy() {
        if(destroyed)
            return

        glDeleteTextures(textureId)
        checkGLErrors()

        super.destroy()
    }

    companion object {
        fun loadImage(file: File, format: ColorFormat = ColorFormat.RGB8): ColorBuffer {
            val stack = MemoryStack.create()

            val w = stack.mallocInt(1)
            val h = stack.mallocInt(1)
            val comp = stack.mallocInt(1)

            if(!stbi_info(file.absolutePath, w, h, comp)) {
                println("Error loading image: ${stbi_failure_reason()}")
            }

            val image = if(format.isFloatingPoint) {
                stbi_loadf(file.absolutePath, w, h, comp, format.channels)
            } else {
                stbi_load(file.absolutePath, w, h, comp, format.channels)
            }

            val cb = ColorBufferGL(
                w[0],
                h[0],
                format,
                MultiSample.None,
                MipMapLevel.None
            )

            cb.bound {
                if(image is FloatBuffer) {
                    glTexImage2D(cb.target, 0, format.internalFormat, cb.width, cb.height, 0, format.glFormat, format.glType, image)
                    stbi_image_free(image)
                }
                if(image is ByteBuffer) {
                    glTexImage2D(cb.target, 0, format.internalFormat, cb.width, cb.height, 0, format.glFormat, format.glType, image)
                    stbi_image_free(image)
                }
            }

            return cb
        }
    }
}