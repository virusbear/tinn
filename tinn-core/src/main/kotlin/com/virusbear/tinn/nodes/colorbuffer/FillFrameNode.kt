package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

//DRAFT
class FillFrameNode: BaseNode("Fill Frame", FillFrameNode) {
    @Register("tinn:fill-frame")
    companion object: NodeIdentifier("Fill Frame", NodeCategory.Utility, factory = { FillFrameNode() })

    val frame: ColorBuffer? by input("Frame", default = null)
    val fillColor: Color by input("Color", default = Color.TRANSPARENT)
    var result: ColorBuffer? by output("Result", default = null)

    override fun process(context: Context) {
        if(frame == null) return

        //TODO: how to avoid copying frame here?
        /*result = ColorBufferPool["FillFrameNode_${id}", ColorBufferPool.Descriptor(IVec2(frame!!.width, frame!!.height))]
        result?.let {
            it.fill(fillColor)
        }*/
    }
}