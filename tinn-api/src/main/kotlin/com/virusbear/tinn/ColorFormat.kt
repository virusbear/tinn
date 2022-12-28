package com.virusbear.tinn

class ColorFormat(val format: PixelFormat, val type: PixelType, val channels: Int, val isFloatingPoint: Boolean) {
    val pixelBytes: Int
        get() = type.bytes * channels

    companion object {
        val R8 = ColorFormat(PixelFormat.R, PixelType.I8, 1, false)
        val R16 = ColorFormat(PixelFormat.R, PixelType.I16, 1, false)
        val R32 = ColorFormat(PixelFormat.R, PixelType.I32, 1, false)
        val R16F = ColorFormat(PixelFormat.R, PixelType.F16, 1, true)
        val R32F = ColorFormat(PixelFormat.R, PixelType.F32, 1, true)

        val RG8 = ColorFormat(PixelFormat.RG, PixelType.I8, 2, false)
        val RG16 = ColorFormat(PixelFormat.RG, PixelType.I16, 2, false)
        val RG32 = ColorFormat(PixelFormat.RG, PixelType.I32, 2, false)
        val RG16F = ColorFormat(PixelFormat.RG, PixelType.F16, 2, true)
        val RG32F = ColorFormat(PixelFormat.RG, PixelType.F32, 2, true)

        val RGB8 = ColorFormat(PixelFormat.RGB, PixelType.I8, 3, false)
        val RGB16 = ColorFormat(PixelFormat.RGB, PixelType.I16, 3, false)
        val RGB32 = ColorFormat(PixelFormat.RGB, PixelType.I32, 3, false)
        val RGB16F = ColorFormat(PixelFormat.RGB, PixelType.F16, 3, true)
        val RGB32F = ColorFormat(PixelFormat.RGB, PixelType.F32, 3, true)

        val RGBA8 = ColorFormat(PixelFormat.RGBA, PixelType.I8, 4, false)
        val RGBA16 = ColorFormat(PixelFormat.RGBA, PixelType.I16, 4, false)
        val RGBA32 = ColorFormat(PixelFormat.RGBA, PixelType.I32, 4, false)
        val RGBA16F = ColorFormat(PixelFormat.RGBA, PixelType.F16, 4, true)
        val RGBA32F = ColorFormat(PixelFormat.RGBA, PixelType.F32, 4, true)

        val DEPTH8 = ColorFormat(PixelFormat.DEPTH, PixelType.I8, 1, false)
        val DEPTH16 = ColorFormat(PixelFormat.DEPTH, PixelType.I16, 1, false)
        val DEPTH32 = ColorFormat(PixelFormat.DEPTH, PixelType.I32, 1, false)
        val DEPTH16F = ColorFormat(PixelFormat.DEPTH, PixelType.F16, 1, true)
        val DEPTH32F = ColorFormat(PixelFormat.DEPTH, PixelType.F32, 1, true)

        val LUMINANCE8 = ColorFormat(PixelFormat.LUMINANCE, PixelType.I8, 1, false)
        val LUMINANCE16 = ColorFormat(PixelFormat.LUMINANCE, PixelType.I16, 1, false)
        val LUMINANCE32 = ColorFormat(PixelFormat.LUMINANCE, PixelType.I32, 1, false)
        val LUMINANCE16F = ColorFormat(PixelFormat.LUMINANCE, PixelType.F16, 1, true)
        val LUMINANCE32F = ColorFormat(PixelFormat.LUMINANCE, PixelType.F32, 1, true)

        val ALPHA8 = ColorFormat(PixelFormat.ALPHA, PixelType.I8, 1, false)
        val ALPHA16 = ColorFormat(PixelFormat.ALPHA, PixelType.I16, 1, false)
        val ALPHA32 = ColorFormat(PixelFormat.ALPHA, PixelType.I32, 1, false)
        val ALPHA16F = ColorFormat(PixelFormat.ALPHA, PixelType.F16, 1, true)
        val ALPHA32F = ColorFormat(PixelFormat.ALPHA, PixelType.F32, 1, true)
    }
}