package com.virusbear.tinn

interface BufferProxy: Destroyable {
    fun download()
    fun upload()
    fun writer(): BufferWriter
    fun reader(): BufferReader
}