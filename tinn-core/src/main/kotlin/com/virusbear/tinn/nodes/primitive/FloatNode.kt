package com.virusbear.tinn.nodes.primitive

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class FloatNode: BaseNode("Float", FloatNode) {
    @Register
    companion object: NodeIdentifier("Float", NodeCategory.Math, { FloatNode() })

    val input: Double by input("Input", default = 0.0)
    var output: Double by output("Output")

    override fun process() {
        output = input
    }
}

