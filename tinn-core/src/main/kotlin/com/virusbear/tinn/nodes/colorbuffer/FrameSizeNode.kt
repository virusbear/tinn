package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.Context
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class FrameSizeNode: BaseNode("Frame size", FrameSizeNode) {
    companion object: NodeIdentifier("Frame size", NodeCategory.Math, factory = { FrameSizeNode() })

    private val frame: ColorBuffer? by input<ColorBuffer?>("Frame")
    private var size: IVec2 by output("Size", default = IVec2.ZERO)

    override fun process(context: Context) {
        frame?.let {
            size = IVec2(it.width, it.height)
        } ?: run {
            size = IVec2.ZERO
        }
    }
}