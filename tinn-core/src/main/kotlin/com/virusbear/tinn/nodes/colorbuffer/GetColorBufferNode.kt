package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class GetColorBufferNode: BaseNode("Get ColorBuffer", GetColorBufferNode) {
    @Register("tinn:get-colorbuffer")
    companion object: NodeIdentifier("Get ColorBuffer", NodeCategory.System, factory = { GetColorBufferNode() })

    val index: String by input("Id", default = "")
    val size: IVec2 by input("Size", IVec2.ZERO)
    var output: ColorBuffer? by output("Output", default = null)

    override fun process(context: Context) {
        if(index.isEmpty()) {
            output = null
            return
        }

        output = ColorBufferPool[index, ColorBufferPool.Descriptor(size)]
    }
}



