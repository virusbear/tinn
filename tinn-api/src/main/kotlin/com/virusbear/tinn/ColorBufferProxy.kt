package com.virusbear.tinn

interface ColorBufferProxy: BufferProxy {
    override fun writer(): ColorBufferWriter
    override fun reader(): ColorBufferReader
}