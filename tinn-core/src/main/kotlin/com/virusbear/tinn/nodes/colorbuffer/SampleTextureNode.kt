package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec4
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class SampleTextureNode: BaseNode("Sample Texture") {
    @Register
    companion object: NodeIdentifier("Sample Texture", NodeCategory.Utility, ::SampleTextureNode)

    val texture: ColorBuffer? by input("Texture", default = null)
    val textureCoords: IVec2 by input("Coordinates", default = IVec2.ZERO)
    var sample: Color by output("Sample", default = Color.TRANSPARENT)

    override fun process() {
        //val proxy = texture?.proxy ?: return
        //proxy.download()
        //TODO: implement buffer proxies
        //val reader = proxy.reader() as ColorBufferReader
        //sample = reader[textureCoords.x, textureCoords.y]
    }
}