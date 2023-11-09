package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class FloatToIntNode: BaseNode("Float to Int", FloatToIntNode) {
    companion object: NodeIdentifier("Float to Int", NodeCategory.Convert, factory = { FloatToIntNode() })

    val input: Double by input("Float", 0.0)
    var output: Int by output("Int", 0)

    override fun process(context: ProcessingContext) {
        output = input.toInt()
    }
}