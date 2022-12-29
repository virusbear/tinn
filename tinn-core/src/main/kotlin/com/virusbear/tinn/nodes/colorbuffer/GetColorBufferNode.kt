package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class GetColorBufferNode: BaseNode("Get ColorBuffer") {
    @Register
    companion object: NodeIdentifier("Get ColorBuffer", NodeCategory.System, ::GetColorBufferNode)

    val index: String by input("Id", default = "")
    val size: IVec2 by input("Size", IVec2.ZERO)
    var output: ColorBuffer? by output("Output", default = null)

    override fun process() {
        if(index.isEmpty()) {
            output = null
            return
        }

        output = ColorBufferPool[index, ColorBufferPool.Descriptor(size)]
    }
}


