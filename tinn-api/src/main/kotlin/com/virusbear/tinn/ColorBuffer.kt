package com.virusbear.tinn

import java.io.File

interface ColorBuffer: Buffer, Bindable, Destroyable {
    val textureId: Int
    val width: Int
    val height: Int
    override val proxy: ColorBufferProxy

    //TODO: implement reading and writing
    fun generateMipMaps()
    fun filter(minifyingFilter: TextureFilter, magnifyingFilter: TextureFilter)
    fun save(file: File)
}