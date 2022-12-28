package com.virusbear.tinn.nodes

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2

class ComposeVec2: BaseNode("Compose Vec2") {
    @Register
    companion object: NodeIdentifier("Vec2", NodeCategory.Compose, ::ComposeVec2)

    val x: Double by input("X", default = 0.0)
    val y: Double by input("Y", default = 0.0)
    var output: Vec2 by output("Output")

    override fun process() {
        output = Vec2(x, y)
    }
}

class ComposeIVec2: BaseNode("Compose IVec2") {
    @Register
    companion object: NodeIdentifier("IVec2", NodeCategory.Compose, ::ComposeVec2)

    val x: Int by input("X", default = 0)
    val y: Int by input("Y", default = 0)
    var output: IVec2 by output("Output")

    override fun process() {
        output = IVec2(x, y)
    }
}