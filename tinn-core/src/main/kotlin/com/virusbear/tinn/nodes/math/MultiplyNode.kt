package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class MultiplyNode: BaseNode("Multiply", MultiplyNode) {
    companion object: NodeIdentifier("Multiply", NodeCategory.Math, factory = { MultiplyNode() })

    val a: Double by input("A", default = 0.0)
    val b: Double by input("B", default = 0.0)
    var result: Double by output("Result")

    override fun process(context: ProcessingContext) {
        result = a * b
    }
}