package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

//DRAFT
class FillFrameNode: BaseNode("Fill Frame") {
    @Register
    companion object: NodeIdentifier("Fill Frame", NodeCategory.Utility, ::FillFrameNode)

    val frame: ColorBuffer? by input("Frame", default = null)
    val fillColor: Color by input("Color", default = Color.TRANSPARENT)
    var result: ColorBuffer? by output("Result", default = null)

    override fun process() {
        if(frame == null) return

        //TODO: how to avoid copying frame here?
        /*result = ColorBufferPool["FillFrameNode_${id}", ColorBufferPool.Descriptor(IVec2(frame!!.width, frame!!.height))]
        result?.let {
            it.fill(fillColor)
        }*/
    }
}