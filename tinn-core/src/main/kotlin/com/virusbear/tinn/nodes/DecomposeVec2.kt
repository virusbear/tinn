package com.virusbear.tinn.nodes

import com.virusbear.tinn.math.Vec2

class DecomposeVec2: BaseNode("Decompose Vec2", DecomposeVec2) {
    @Register
    companion object : NodeIdentifier("Vec2", NodeCategory.Decompose, { DecomposeVec2() })

    val input: Vec2 by input("Input", default = Vec2.ZERO)
    var x: Double by output("X")
    var y: Double by output("Y")

    override fun process() {
        x = input.x
        y = input.y
    }
}