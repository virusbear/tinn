package com.virusbear.tinn.nodes

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.math.IVec2

class FrameSizeNode: BaseNode("Frame size") {
    @Register
    companion object: NodeIdentifier("Frame size", NodeCategory.Math, ::FrameSizeNode)

    private val frame: ColorBuffer? by input("Frame")
    private var size: IVec2 by output("Size", default = IVec2.ZERO)

    override fun process() {
        frame?.let {
            size = IVec2(it.width, it.height)
        } ?: run {
            size = IVec2.ZERO
        }
    }
}