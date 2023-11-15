package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import org.lwjgl.opengl.GL33C.*
import org.lwjgl.stb.STBImage.*
import org.lwjgl.stb.STBImageWrite.stbi_write_png
import org.lwjgl.system.MemoryStack
import java.io.File
import java.nio.ByteBuffer
import java.nio.FloatBuffer

class ColorBufferGL internal constructor(
    override val width: Int,
    override val height: Int,
    override val contentScale: Double,
    val format: ColorFormat,
    override val  multisample: MultiSample,
    val levels: MipMapLevel,
    private val context: GraphicsContextGL,
    driver: Driver
): ColorBuffer, Trackable(driver) {
    internal val target: Int
    val textureId: Int

    override val proxy: ColorBufferProxy = ColorBufferProxyGL(this)

    init {
        require(width < LimitsGL.MaxTextureSize)
        require(height < LimitsGL.MaxTextureSize)

        textureId = context.glGenTextures()
        context.checkGLErrors()

        target = when(multisample) {
            MultiSample.None -> GL_TEXTURE_2D
            else -> GL_TEXTURE_2D_MULTISAMPLE
        }

        bound {
            if(levels != MipMapLevel.None) {
                context.glTexParameteri(target, GL_TEXTURE_MAX_LEVEL, levels.levels - 1)
            }

            context.glPixelStorei(GL_UNPACK_ROW_LENGTH, 0)
            
            when(multisample) {
                MultiSample.None -> context.glTexImage2D(target, 0, format.internalFormat, width, height, 0, format.glFormat, format.glType)
                else -> context.glTexImage2DMultisample(target, multisample.samples.coerceAtMost(LimitsGL.MaxSamples - 1), format.internalFormat, width, height, false)
            }
            context.checkGLErrors()
        }

        filter()
    }

    override fun generateMipMaps() {
        require(multisample == MultiSample.None) { "Multisampled mipmaps not supported" }
        if(levels == MipMapLevel.None)
            return

        bound {
            context.glGenerateMipmap(target)
        }
    }

    override fun filter(minifyingFilter: TextureFilter, magnifyingFilter: TextureFilter) {
        bound {
            context.glTexParameteri(target, GL_TEXTURE_MIN_FILTER, minifyingFilter.gl)
            context.glTexParameteri(target, GL_TEXTURE_MAG_FILTER, magnifyingFilter.gl)
        }
    }

    override fun save(file: File) {
        val proxy = proxy.also { it.download() } as ColorBufferProxyGL
        val imageBuffer = proxy.buffer ?: return

        stbi_write_png(file.absolutePath, width, height, format.channels, imageBuffer, width * format.pixelBytes)
    }

    override fun bind() {
        require(!destroyed) { "ColorBuffer is destroyed" }

        context.glActiveTexture(GL_TEXTURE0)
        context.glBindTexture(target, textureId)
        context.checkGLErrors()
    }

    override fun unbind() {
        context.glBindTexture(target, 0)
        context.checkGLErrors()
    }

    override fun destroy() {
        if(destroyed)
            return

        context.glDeleteTextures(textureId)
        context.checkGLErrors()

        super.destroy()
    }

    companion object {
        fun loadImage(file: File, format: ColorFormat = ColorFormat.RGB8, context: GraphicsContextGL, driver: Driver): ColorBuffer {
            val stack = MemoryStack.stackPush()

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
                1.0,
                format,
                MultiSample.None,
                MipMapLevel.None,
                context,
                driver
            )

            cb.bound {
                if(image is FloatBuffer) {
                    cb.context.glTexImage2D(cb.target, 0, format.internalFormat, cb.width, cb.height, 0, format.glFormat, format.glType, image)
                    stbi_image_free(image)
                }
                if(image is ByteBuffer) {
                    cb.context.glTexImage2D(cb.target, 0, format.internalFormat, cb.width, cb.height, 0, format.glFormat, format.glType, image)
                    stbi_image_free(image)
                }
            }

            return cb
        }
    }
}