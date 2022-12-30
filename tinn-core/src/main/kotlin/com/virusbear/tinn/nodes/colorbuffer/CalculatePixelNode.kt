package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.ColorBufferProxy
import com.virusbear.tinn.ColorBufferReader
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.nodes.*

class CalculatePixelNode: GroupNode("Calculate Pixel", CalculatePixelNode) {
    @Register
    companion object: NodeIdentifier("Calculate Pixel", NodeCategory.Utility, { CalculatePixelNode() })

    val image: ColorBuffer? by input("Image", default = null)
    var output: ColorBuffer? by output("Output", default = null)

    val pixelInputPort: Port = inputNode.addPort(PortDirection.Output, "Pixel", Color::class, Color.TRANSPARENT)
    val positionInputPort: Port = inputNode.addPort(PortDirection.Output, "Position", IVec2::class, IVec2.ZERO)
    val sizeInputPort: Port = inputNode.addPort(PortDirection.Output, "Size", IVec2::class, IVec2.ZERO)
    val readerInputPort: Port = inputNode.addPort(PortDirection.Output, "Reader", ColorBufferReader::class, null)
    val pixelOutputPort: Port = outputNode.addPort(PortDirection.Input, "Pixel", Color::class, Color.TRANSPARENT)

    override fun process() {
        propagate()

        val img = image ?: run {
            output = null
            return
        }
        val result = ColorBufferPool["CalculatePixelNode_$id", ColorBufferPool.Descriptor(IVec2(img.width, img.height))]

        val reader = img.proxy.also(ColorBufferProxy::download).reader()
        val resultProxy = result.proxy.also(ColorBufferProxy::download)
        val writer = resultProxy.writer()

        sizeInputPort.value = IVec2(img.width, img.height)
        readerInputPort.value = reader

        for(x in 0 until img.width) {
            for(y in 0 until img.height) {
                pixelInputPort.value = reader[x, y]
                positionInputPort.value = IVec2(x, y)
                contentNodespace.evaluate()
                writer[x, y] = pixelOutputPort.value as Color
            }
        }

        resultProxy.upload()
        output = result

        outputNode.propagate()
    }
}