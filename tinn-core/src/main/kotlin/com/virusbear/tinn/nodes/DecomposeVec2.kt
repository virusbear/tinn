package com.virusbear.tinn.nodes

import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class DecomposeVec2: BaseNode("Decompose Vec2", DecomposeVec2) {
    @Register("tinn:decompose-vec2")
    companion object : NodeIdentifier("Vec2", NodeCategory.Decompose, factory = { DecomposeVec2() })

    val input: Vec2 by input("Input", default = Vec2.ZERO)
    var x: Double by output("X")
    var y: Double by output("Y")

    override fun process(context: Context) {
        x = input.x
        y = input.y
    }
}