package com.virusbear.tinn.nodes.primitive

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context

class FloatNode: BaseNode("Float", FloatNode) {
    @Register
    companion object: NodeIdentifier("Float", NodeCategory.Math, factory = { FloatNode() })

    val input: Double by input("Input", default = 0.0)
    var output: Double by output("Output")

    override fun process(context: Context) {
        output = input
    }
}

