package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class IVec2ToVec2: BaseNode("IVec2 to Vec2", IVec2ToVec2) {
    companion object: NodeIdentifier("IVec2 to Vec2", NodeCategory.Convert, factory = { Vec2ToIVec2() })

    val iVec2: IVec2 by input("Input", default = IVec2.ZERO)
    var vec2: Vec2 by output("Output", default = Vec2.ZERO)

    override fun process(context: ProcessingContext) {
        vec2 = Vec2(iVec2.x.toDouble(), iVec2.y.toDouble())
    }
}