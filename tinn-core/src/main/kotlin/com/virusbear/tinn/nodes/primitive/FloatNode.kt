package com.virusbear.tinn.nodes.primitive

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class FloatNode: BaseNode("Float", FloatNode) {
    companion object: NodeIdentifier("Float", NodeCategory.Math, factory = { FloatNode() })

    val input: Double by input("Input", default = 0.0)
    var output: Double by output("Output")

    override fun process(context: ProcessingContext) {
        output = input
    }
}

