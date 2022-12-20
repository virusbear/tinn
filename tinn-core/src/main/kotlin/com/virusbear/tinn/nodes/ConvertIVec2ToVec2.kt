package com.virusbear.tinn.nodes

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2

class ConvertIVec2ToVec2: BaseNode("Convert IVec2 to Vec2") {
    @Register
    companion object: NodeIdentifier("IVec2 to Vec2", NodeCategory.Convert, ::ConvertIVec2ToVec2)

    val iVec2: IVec2 by input("Input", default = IVec2.ZERO)
    var vec2: Vec2 by output("Output", default = Vec2.ZERO)

    override fun process() {
        vec2 = Vec2(iVec2.x.toDouble(), iVec2.y.toDouble())
    }
}