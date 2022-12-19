package com.virusbear.tinn.nodes

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2

class ComposeVec2: BaseNode("Compose Vec2") {
    @Register
    companion object: NodeIdentifier("Vec2", NodeCategory.Compose, ::ComposeVec2)

    val x: Double by input("X")
    val y: Double by input("Y")
    var output: Vec2 by output("Output")

    override fun process() {
        output = Vec2(x, y)
    }
}

class ConvertIVec2ToVec2: BaseNode("Convert IVec2 to Vec2") {
    @Register
    companion object: NodeIdentifier("IVec2 to Vec2", NodeCategory.Convert, ::ConvertIVec2ToVec2)

    val iVec2: IVec2 by input("Input")
    var vec2: Vec2 by output("Output")

    override fun process() {
        vec2 = Vec2(iVec2.x.toDouble(), iVec2.y.toDouble())
    }
}