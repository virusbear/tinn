package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.Context
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class Vec2ToIVec2: BaseNode("Vec2 to IVec2", IVec2ToVec2) {
    companion object: NodeIdentifier("Vec2 to IVec2", NodeCategory.Convert, factory = { Vec2ToIVec2() })

    val vec2: Vec2 by input("Input", default = Vec2.ZERO)
    var iVec2: IVec2 by output("Output", default = IVec2.ZERO)

    override fun process(context: Context) {
        iVec2 = IVec2(vec2.x.toInt(), vec2.y.toInt())
    }
}