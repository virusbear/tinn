package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class SubtractNode: BaseNode("Subtract", SubtractNode) {
    @Register
    companion object: NodeIdentifier("Subtract", NodeCategory.Math, { SubtractNode() })

    val a: Double by input("A", default = 0.0)
    val b: Double by input("B", default = 0.0)
    var result: Double by output("Result")

    override fun process() {
        result = a - b
    }
}