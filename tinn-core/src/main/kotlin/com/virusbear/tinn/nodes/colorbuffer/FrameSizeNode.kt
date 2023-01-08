package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context

class FrameSizeNode: BaseNode("Frame size", FrameSizeNode) {
    @Register
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