package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.GL_LUMINANCE
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL41C

internal val ColorFormat.internalFormat: Int
    get() =
        when(this) {
            ColorFormat.R8 -> GL_R8
            ColorFormat.R16 -> GL_R16
            ColorFormat.R32 -> GL_R32UI
            ColorFormat.R16F -> GL_R16F
            ColorFormat.R32F -> GL_R32F
            ColorFormat.RG8 -> GL_RG8
            ColorFormat.RG16 -> GL_RG16
            ColorFormat.RG32 -> GL_RG32UI
            ColorFormat.RG16F -> GL_RG16F
            ColorFormat.RG32F -> GL_RG32F
            ColorFormat.RGB8 -> GL_RGB8
            ColorFormat.RGB16 -> GL_RGB16
            ColorFormat.RGB32 -> GL_RGB32UI
            ColorFormat.RGB16F -> GL_RGB16F
            ColorFormat.RGB32F -> GL_RGB32F
            ColorFormat.RGBA8 -> GL_RGBA8
            ColorFormat.RGBA16 -> GL_RGBA16
            ColorFormat.RGBA32 -> GL_RGBA32UI
            ColorFormat.RGBA16F -> GL_RGBA16F
            ColorFormat.RGBA32F -> GL_RGBA32F
            ColorFormat.DEPTH16 -> GL_DEPTH_COMPONENT16
            ColorFormat.LUMINANCE8, ColorFormat.LUMINANCE16, ColorFormat.LUMINANCE32, ColorFormat.LUMINANCE16F, ColorFormat.LUMINANCE32F -> GL11.GL_LUMINANCE
            ColorFormat.ALPHA8, ColorFormat.ALPHA16, ColorFormat.ALPHA32, ColorFormat.ALPHA16F, ColorFormat.ALPHA32F -> GL_ALPHA
            else -> error("ColorFormat not supported")
        }

internal val ColorFormat.glType: Int
    get() =
        when(this.type) {
            PixelType.I8 -> GL_UNSIGNED_BYTE
            PixelType.I16 -> GL_UNSIGNED_SHORT
            PixelType.I32 -> GL_UNSIGNED_INT
            PixelType.F16 -> GL_HALF_FLOAT
            PixelType.F32 -> GL_FLOAT
        }

internal val ColorFormat.glFormat: Int
    get() =
        when(this.format) {
            PixelFormat.R -> GL_RED
            PixelFormat.RG -> GL_RG
            PixelFormat.RGB -> GL_RGB
            PixelFormat.RGBA -> GL_RGBA
            PixelFormat.DEPTH -> GL_DEPTH_COMPONENT
            PixelFormat.LUMINANCE -> GL_LUMINANCE
            PixelFormat.ALPHA -> GL_ALPHA
        }

internal val VertexComponentType.gl: Int
    get() = when(this) {
        VertexComponentType.HALF_FLOAT -> GL_HALF_FLOAT
        VertexComponentType.FLOAT -> GL_FLOAT
        VertexComponentType.DOUBLE -> GL_DOUBLE
        VertexComponentType.FIXED -> GL41C.GL_FIXED
        VertexComponentType.BYTE -> GL_BYTE
        VertexComponentType.UBYTE -> GL_UNSIGNED_BYTE
        VertexComponentType.SHORT -> GL_SHORT
        VertexComponentType.USHORT -> GL_UNSIGNED_SHORT
        VertexComponentType.INT -> GL_INT
        VertexComponentType.UINT -> GL_UNSIGNED_INT
    }

internal val TextureFilter.gl: Int
    get() = when(this) {
        TextureFilter.NEAREST -> GL_NEAREST
        TextureFilter.LINEAR -> GL_LINEAR
    }