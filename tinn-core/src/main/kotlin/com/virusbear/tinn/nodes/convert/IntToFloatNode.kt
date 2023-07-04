package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.Context
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class IntToFloatNode: BaseNode("Int to Float", IntToFloatNode) {
    companion object: NodeIdentifier("Int to Float", NodeCategory.Convert, factory = { IntToFloatNode() })

    val input: Int by input("Int", 0)
    var output: Double by output("Float", 0.0)

    override fun process(context: Context) {
        output = input.toDouble()
    }
}