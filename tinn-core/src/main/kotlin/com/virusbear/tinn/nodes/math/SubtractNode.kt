package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class SubtractNode: BaseNode("Subtract", SubtractNode) {
    companion object: NodeIdentifier("Subtract", NodeCategory.Math, factory = { SubtractNode() })

    val a: Double by input("A", default = 0.0)
    val b: Double by input("B", default = 0.0)
    var result: Double by output("Result")

    override fun process(context: ProcessingContext) {
        result = a - b
    }
}