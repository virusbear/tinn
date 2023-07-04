package com.virusbear.tinn.nodes

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class ConvertIVec2ToVec2: BaseNode("Convert IVec2 to Vec2", ConvertIVec2ToVec2) {
    @Register("tinn:convert-ivec2-vec2")
    companion object: NodeIdentifier("IVec2 to Vec2", NodeCategory.Convert, factory = { ConvertIVec2ToVec2() })

    val iVec2: IVec2 by input("Input", default = IVec2.ZERO)
    var vec2: Vec2 by output("Output", default = Vec2.ZERO)

    override fun process(context: Context) {
        vec2 = Vec2(iVec2.x.toDouble(), iVec2.y.toDouble())
    }
}