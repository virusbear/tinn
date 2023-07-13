package com.virusbear.tinn

import com.virusbear.tinn.math.IVec2
import java.io.File

interface ColorBuffer: Buffer, Bindable, Destroyable {
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
    fun filter(minifyingFilter: TextureFilter = TextureFilter.LINEAR, magnifyingFilter: TextureFilter = TextureFilter.LINEAR)
    fun save(file: File)
}