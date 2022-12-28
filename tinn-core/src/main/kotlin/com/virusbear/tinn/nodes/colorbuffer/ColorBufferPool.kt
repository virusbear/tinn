package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.*
import com.virusbear.tinn.math.IVec2

//TODO: when to deallocate not used buffers?
object ColorBufferPool: BufferPool<ColorBufferPool.Descriptor, ColorBuffer>({ ColorBufferPool.create(it) }, ColorBuffer::destroy) {
    private fun create(descriptor: Descriptor): ColorBuffer =
        Driver.driver.createColorBuffer(descriptor.size.x, descriptor.size.y, ColorFormat.RGBA8, MultiSample.None, MipMapLevel.None)

    data class Descriptor(
        val size: IVec2
    )
}