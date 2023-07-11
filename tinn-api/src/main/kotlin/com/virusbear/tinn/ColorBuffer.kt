package com.virusbear.tinn

import com.virusbear.tinn.math.IVec2
import java.io.File

interface ColorBuffer: Buffer, Bindable, Destroyable {
    val textureId: Int
    val width: Int
    val height: Int
    val size: IVec2
        get() = IVec2(width, height)

    val contentScale: Double
    val effectiveWidth: Int
        get() = (width * contentScale).toInt()
    val effectiveHeight: Int
        get() = (height * contentScale).toInt()
    val effectiveSize: IVec2
        get() = IVec2(effectiveWidth, effectiveHeight)

    val multisample: MultiSample
    override val proxy: ColorBufferProxy

    //TODO: implement reading and writing
    fun generateMipMaps()
    fun filter(minifyingFilter: TextureFilter, magnifyingFilter: TextureFilter)
    fun save(file: File)
}

fun colorBuffer(
    width: Int,
    height: Int,
    contentScale: Double = 1.0,
    multisample: MultiSample = MultiSample.None,
    format: ColorFormat = ColorFormat.RGBA8,
    levels: MipMapLevel = MipMapLevel.None
): ColorBuffer =
    Driver.driver.createColorBuffer(width, height, contentScale, format, multisample, levels)