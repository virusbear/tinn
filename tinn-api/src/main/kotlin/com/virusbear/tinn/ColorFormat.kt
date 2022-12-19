package com.virusbear.tinn

class ColorFormat(val format: PixelFormat, val type: PixelType) {
    companion object {
        val R8 = ColorFormat(PixelFormat.R, PixelType.I8)
        val R16 = ColorFormat(PixelFormat.R, PixelType.I16)
        val R32 = ColorFormat(PixelFormat.R, PixelType.I32)
        val R16F = ColorFormat(PixelFormat.R, PixelType.F16)
        val R32F = ColorFormat(PixelFormat.R, PixelType.F32)

        val RG8 = ColorFormat(PixelFormat.RG, PixelType.I8)
        val RG16 = ColorFormat(PixelFormat.RG, PixelType.I16)
        val RG32 = ColorFormat(PixelFormat.RG, PixelType.I32)
        val RG16F = ColorFormat(PixelFormat.RG, PixelType.F16)
        val RG32F = ColorFormat(PixelFormat.RG, PixelType.F32)

        val RGB8 = ColorFormat(PixelFormat.RGB, PixelType.I8)
        val RGB16 = ColorFormat(PixelFormat.RGB, PixelType.I16)
        val RGB32 = ColorFormat(PixelFormat.RGB, PixelType.I32)
        val RGB16F = ColorFormat(PixelFormat.RGB, PixelType.F16)
        val RGB32F = ColorFormat(PixelFormat.RGB, PixelType.F32)

        val RGBA8 = ColorFormat(PixelFormat.RGBA, PixelType.I8)
        val RGBA16 = ColorFormat(PixelFormat.RGBA, PixelType.I16)
        val RGBA32 = ColorFormat(PixelFormat.RGBA, PixelType.I32)
        val RGBA16F = ColorFormat(PixelFormat.RGBA, PixelType.F16)
        val RGBA32F = ColorFormat(PixelFormat.RGBA, PixelType.F32)

        val DEPTH8 = ColorFormat(PixelFormat.DEPTH, PixelType.I8)
        val DEPTH16 = ColorFormat(PixelFormat.DEPTH, PixelType.I16)
        val DEPTH32 = ColorFormat(PixelFormat.DEPTH, PixelType.I32)
        val DEPTH16F = ColorFormat(PixelFormat.DEPTH, PixelType.F16)
        val DEPTH32F = ColorFormat(PixelFormat.DEPTH, PixelType.F32)

        val LUMINANCE8 = ColorFormat(PixelFormat.LUMINANCE, PixelType.I8)
        val LUMINANCE16 = ColorFormat(PixelFormat.LUMINANCE, PixelType.I16)
        val LUMINANCE32 = ColorFormat(PixelFormat.LUMINANCE, PixelType.I32)
        val LUMINANCE16F = ColorFormat(PixelFormat.LUMINANCE, PixelType.F16)
        val LUMINANCE32F = ColorFormat(PixelFormat.LUMINANCE, PixelType.F32)

        val ALPHA8 = ColorFormat(PixelFormat.ALPHA, PixelType.I8)
        val ALPHA16 = ColorFormat(PixelFormat.ALPHA, PixelType.I16)
        val ALPHA32 = ColorFormat(PixelFormat.ALPHA, PixelType.I32)
        val ALPHA16F = ColorFormat(PixelFormat.ALPHA, PixelType.F16)
        val ALPHA32F = ColorFormat(PixelFormat.ALPHA, PixelType.F32)
    }
}