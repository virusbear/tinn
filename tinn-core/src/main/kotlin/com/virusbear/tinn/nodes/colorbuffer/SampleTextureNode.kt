package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.Context
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class SampleTextureNode: BaseNode("Sample Texture", SampleTextureNode) {
    companion object: NodeIdentifier("Sample Texture", NodeCategory.Utility, factory = { SampleTextureNode() })

    val texture: ColorBuffer? by input("Texture", default = null)
    val textureCoords: IVec2 by input("Coordinates", default = IVec2.ZERO)
    var sample: Color by output("Sample", default = Color.TRANSPARENT)

    override fun process(context: Context) {
        val proxy = texture?.proxy ?: return
        //TODO: optimize as downloading whole texture might be a difficult thing todo
        proxy.download()
        val reader = proxy.reader()
        sample = reader[textureCoords.x, textureCoords.y]
    }
}